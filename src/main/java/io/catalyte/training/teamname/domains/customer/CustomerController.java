package io.catalyte.training.teamname.domains.customer;

import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
@ApiResponses(value = {
    @ApiResponse(code = 500, message = "Server Error", response = ServiceUnavailable.class),
    @ApiResponse(code = 503, message = "Service Unavailable", response = DataAccessException.class)
})
public class CustomerController {

  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
  @Autowired
  private CustomerService customerService;

  /**
   * Maps a GET request to CustomerService that retrieves a customer or customers
   *
   * @param customer
   * @return a customer or customers based on filtered field
   */
  @GetMapping
  @ApiOperation("Queries Customers")
  @ApiResponse(code = 200, message = "OK", response = Customer.class)
  public ResponseEntity<List<Customer>> queryCustomers(Customer customer) {
    logger.info(new Date() + " Query request received: " + customer.toString());

    return new ResponseEntity<>(customerService.queryCustomers(customer), HttpStatus.OK);
  }

  /**
   * Maps a POST request to CustomerService that adds a new customer to the collection
   *
   * @param customer a Customer object containing all required fields
   * @return The customer that was entered into the collection
   */
  @PostMapping
  @ApiOperation("Adds a new Customer")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "CREATED", response = Customer.class),
      @ApiResponse(code = 409, message = "CONFLICT"),
      @ApiResponse(code = 400, message = "BAD REQUEST")
  })
  public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
    logger.info(new Date() + " Post request received");

    return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
  }

  /**
   * Maps a GET request to CustomerService that retrieves a customer
   *
   * @param email the email associated with the customer
   * @return a customer with a matching email
   */
  @GetMapping(value = "/{email}")
  @ApiOperation("Get Customer By email")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Customer.class),
      @ApiResponse(code = 404, message = "NOT FOUND")
  })
  public ResponseEntity<Customer> findByEmail(@PathVariable String email) {
    logger.info(new Date() + " Get by email " + email + " request received");

    return new ResponseEntity<>(customerService.findByEmail(email), HttpStatus.OK);
  }


}
