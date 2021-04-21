package io.catalyte.training.teamname.security;

import static io.catalyte.training.teamname.constants.StringConstants.INVALID_EMAIL_PASSWORD;
import static io.catalyte.training.teamname.constants.StringConstants.ISSUER;
import static io.catalyte.training.teamname.constants.StringConstants.SECRET_KEY;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.catalyte.training.teamname.domains.customer.Customer;
import io.catalyte.training.teamname.domains.customer.CustomerService;
import io.catalyte.training.teamname.exceptions.BadRequest;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final CustomerService customerService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthServiceImpl(CustomerService customerService,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.customerService = customerService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /**
   * This methods accepts credentials as a parameter. When it is supplied, we check to see to the
   * email and password given match the email and encoded password we have saved in our system. If
   * they match we are returned a jwt token. Otherwise, we throw a 400 Bad Request.
   *
   * @param credentials - takes in a set of credentials which at this moment (4.19.21) is a
   *                    preloaded user email and encoded password
   * @return a jwt token
   */
  @Override
  public AuthToken login(Credential credentials) {
    if (credentials.getEmail() == null || credentials.getPassword() == null) {
      throw new BadRequest(INVALID_EMAIL_PASSWORD);
    }

    String email = credentials.getEmail();
    String password = credentials.getPassword();

    Customer customer = customerService.findByEmail(email);

    if (customer == null) {
      throw new BadRequest(INVALID_EMAIL_PASSWORD);
    }

    String passwordOnFile = customer.getPassword();

    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    String jwtToken =
        JWT.create()
            .withIssuer(ISSUER)
            .withSubject(email)
            .withIssuedAt(new Date())
            .sign(algorithm);

    return new AuthToken(jwtToken);
  }
}
