package io.catalyte.training.teamname.security;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  /**
   * Sends an email and password to the login endpoint for verification.
   *
   * @param credentials an email and password
   * @return the login credential token.
   */

  @PostMapping
  AuthToken login(@Valid @RequestBody Credential credentials) {
    return authService.login(credentials);
  }
}

