package pl.ryspie.springbootecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryspie.springbootecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
