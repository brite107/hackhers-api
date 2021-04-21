package io.catalyte.training.teamname.domains.customer;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.catalyte.training.teamname.exceptions.ResourceNotFound;
import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;


import static org.mockito.Mockito.*;

/**
 * Tests the functioning of the customerServiceImpl class
 */
public class CustomerServiceImplTest {

  @Mock
  CustomerRepo customerRepo;
  @InjectMocks
  CustomerServiceImpl customerServiceImpl;
  String testEmail;
  Customer testCustomer;
  List<Customer> testList = new ArrayList<>();


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    testEmail = "lizaTownsend@gmail.com";
    testCustomer = new Customer("Liza", "Townsend", "Password123$","lizaTownsend@gmail.com",
        "312-480-7899", "111 Bonny Lane", "Los Angeles",
        "CA", "92310");
    testCustomer.setId(1L);
    testList.add(testCustomer);
    when(customerRepo.save(any(Customer.class))).thenReturn(testList.get(0));
    when(customerRepo.findAll()).thenReturn(testList);
    when(customerRepo.findAll(any(Example.class))).thenReturn(testList);

  }

//  @Test
//  public void queryCustomers() {
//    List<Customer> result = customerServiceImpl.queryCustomers(new Customer());
//    Assertions.assertEquals(testList, result);
//  }
//
//  @Test(expected = ServiceUnavailable.class)
//  public void queryCustomersDBError() {
//    when(customerRepo.findAll()).thenThrow(EmptyResultDataAccessException.class);
//    customerServiceImpl.queryCustomers(new Customer());
//  }
//
//  @Test
//  public void addCustomer() {
//    Customer result = customerServiceImpl.addCustomer(testCustomer);
//    Assertions.assertEquals(testCustomer, result);
//  }
//
//  @Test(expected = ServiceUnavailable.class)
//  public void addCustomerDBError() {
//    when(customerRepo.save(any(Customer.class))).thenThrow(EmptyResultDataAccessException.class);
//    customerServiceImpl.addCustomer(testCustomer);
//  }
//
//  @Test
//  public void getCustomer() {
//    Customer result = customerServiceImpl.findByEmail("lizaTownsend@gmail.com");
//    Assertions.assertEquals(testEmail, result);
//  }
//
//  @Test(expected = ServiceUnavailable.class)
//  public void getCustomerDBError() {
//    when(customerRepo.findById(anyLong())).thenThrow(EmptyResultDataAccessException.class);
//    customerServiceImpl.findByEmail("lizaTownsend@gmail.com");
//  }
//
//  @Test(expected = ResourceNotFound.class)
//  public void getCustomerNotFound() {
//    when(customerRepo.findById(anyLong())).thenReturn(Optional.empty());
//    customerServiceImpl.findByEmail("lizaTownsend@gmail.com");
//  }
}