import com.example.data_service.Customer;
import com.example.data_service.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmail() {
        Customer customer = new Customer("Kasia", "kasia@gmail.com", "password");
        customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findByEmail("kasia@gmail.com");

        assertTrue(foundCustomer.isPresent());
    }
}