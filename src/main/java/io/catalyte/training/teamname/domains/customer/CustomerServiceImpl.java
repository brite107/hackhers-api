package io.catalyte.training.teamname.domains.customer;

import static io.catalyte.training.teamname.constants.StringConstants.BAD_REQUEST_CUSTOMER_NOT_FOUND;
import static io.catalyte.training.teamname.constants.StringConstants.BAD_REQUEST_ID;
import static io.catalyte.training.teamname.constants.StringConstants.EMAIL_CONFLICT;

import io.catalyte.training.teamname.exceptions.BadDataResponse;
import io.catalyte.training.teamname.exceptions.ResourceNotFound;
import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.catalyte.training.teamname.exceptions.UniqueFieldViolation;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implements CustomerService and performs the business logic required for CRUD operations on the
 * Customer domain.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private CustomerRepo customerRepo;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public CustomerServiceImpl(CustomerRepo customerRepo,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.customerRepo = customerRepo;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /**
   * Retrieves Customers based on query filters
   *
   * @param customer the customer object
   * @return matching customers
   */
  public List<Customer> queryCustomers(Customer customer) {
    try {
      if (customer.isEmpty()) {
        return customerRepo.findAll();
      } else {
        Example<Customer> customerExample = Example.of(customer);
        return customerRepo.findAll(customerExample);
      }
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
  }

  /**
   * Adds a new Customer to the collection
   *
   * @param customer the new Customer object
   * @return The customer that was just added to the collection
   */
  public Customer addCustomer(Customer customer) {
    boolean emailAlreadyExists;

    try {
      // check if email already exists
      emailAlreadyExists = customerRepo.existsByEmail(customer.getEmail());

      if (!emailAlreadyExists) {
        // encrypt password
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
      }
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }

    // if made it to this point, email is not unique
    throw new UniqueFieldViolation("Email is already in use");
  }

  /**
   * Retrieves a single Customer by email
   *
   * @param email the email associated with the Customer
   * @return the matching Customer
   */
  public Customer findByEmail(String email) {
    try {
      Customer customerLookupResult = customerRepo.findCustomerByEmail(email);
      if (customerLookupResult != null) {
        return customerLookupResult;
      }

    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
    throw new ResourceNotFound("Could not locate a customer with the email: " + email);
  }

  /**
   * Updates a customer with a specific id
   *
   * @param id       the id of the customer to be updated
   * @param customer the customer's new information
   * @return the customer's new information
   */
  public Customer updateCustomerById(Long id, Customer customer) {

    Customer existingCustomer;

    boolean emailIsSame;
    boolean newEmailIsUnique;
    String currentEmail;

    // get the new email from the customer passed int
    String newEmail = customer.getEmail();

    // check if id in path matches id in request body
    if (!customer.getId().equals(id)) {
      throw new BadDataResponse(BAD_REQUEST_ID);
    }

    try {

      // get the existing customer from the database
      existingCustomer = customerRepo.findById(id).orElse(null);

      if (existingCustomer != null) {

        // get the current email from the database
        currentEmail = existingCustomer.getEmail();

        // see if new email already exists
        newEmailIsUnique = !customerRepo.existsByEmail(newEmail);

        // set local for email is same
        emailIsSame = currentEmail.equals(newEmail);

        // only continue if email has not changed, or new email is unique
        if (emailIsSame || newEmailIsUnique) {
          // encrypt password
          customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
          return customerRepo.save(customer);
        }
      }
    } catch (Exception e) {
      logger.error(new Date() + e.getMessage());
      throw new ServiceUnavailable(e);
    }

    // if user was not found...
    if (existingCustomer == null) {
      throw new ResourceNotFound(BAD_REQUEST_CUSTOMER_NOT_FOUND);
    }
    // customer was found so it must because of an email conflict
    else {
      throw new UniqueFieldViolation(EMAIL_CONFLICT);
    }

  }

}