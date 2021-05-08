package io.catalyte.training.teamname.domains.customer;

import java.util.List;

/**
 * The CustomerService interface defines the methods required to implement the CustomerService
 * class
 */
public interface CustomerService {

  Customer addCustomer(Customer customer);

  List<Customer> queryCustomers(Customer customer);

  Customer findByEmail(String email);

  Customer updateCustomerById(Long id, Customer customer);

}

