import com.example.data_service.Customer;
import com.example.data_service.CustomerRepository;
import com.example.data_service.DataController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DataControllerTest {

    @InjectMocks
    private DataController dataController;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById_Found() {
        Customer customer = new Customer("Kasia", "kasia@gmail.com", "password");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = dataController.getCustomerById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = dataController.getCustomerById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }
}