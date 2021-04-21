package io.catalyte.training.teamname.domains.customer;

import io.catalyte.training.teamname.exceptions.ResourceNotFound;
import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.catalyte.training.teamname.exceptions.UniqueFieldViolation;
import java.security.SecureRandom;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Implements CustomerService and performs the business logic required for CRUD operations on the
 * Customer domain.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
  @Autowired
  private CustomerRepo customerRepo;
  private Customer customer;

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
    if (customerRepo.existsByEmail(customer.getEmail())) {
      throw new UniqueFieldViolation(customer.getEmail() + " already exists");
    }
    try {
      return customerRepo.save(customer);
    } catch (Exception e) {
      throw new ServiceUnavailable(e);
    }
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

}