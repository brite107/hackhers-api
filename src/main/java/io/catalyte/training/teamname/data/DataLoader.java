package io.catalyte.training.teamname.data;

import io.catalyte.training.teamname.domains.customer.Customer;
import io.catalyte.training.teamname.domains.customer.CustomerRepo;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Loads the data for Customer
 */
@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private CustomerRepo customerRepo;
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,
      new SecureRandom());

  private Customer customerOne;
  private Customer customerTwo;
  private Customer customerThree;
  private Customer customerFour;

  @Override
  public void run(String... strings) {
    loadCustomers();
  }

  private void loadCustomers() {

    customerOne = customerRepo.save(
        new Customer("Jen", "Perry", bCryptPasswordEncoder.encode("I@mJen41"), "jperry@mail.com",
            "312-333-3333", "1222 Developer Way", "Chicago",
            "IL", "60466"));
    customerTwo = customerRepo.save(
        new Customer("Shannon", "Goupil", bCryptPasswordEncoder.encode("I@mShannon4"),
            "sgoupil@mail.com",
            "312-345-3333", "1234 Coder Way",
            "Colorado Springs", "CO", "97124"));
    customerThree = customerRepo.save(
        new Customer("Catherine", "Booker", bCryptPasswordEncoder.encode("I@mCat42"),
            "cbooker@mail.com",
            "312-555-3333", "1578 HackHer Way", "Laurel", "MD",
            "97267"));
    customerFour = customerRepo.save(
        new Customer("Heidi", "Edmonds", bCryptPasswordEncoder.encode("I@mHeidi4"),
            "hedmunds@mail.com", "312-678-3333", "1290 Scrum Way",
            "Cheyenne", "WY",
            "92310"));
  }
}

