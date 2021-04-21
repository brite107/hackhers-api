package io.catalyte.training.teamname.domains.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The CustomerRepo defines required methods for repository functionality
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

  boolean existsByEmail(String email);

  Customer findCustomerByEmail(String email);

}

