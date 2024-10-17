import com.example.account_service.AccountController;
import com.example.account_service.User;
import com.example.account_service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        User user = new User("Kasia", "kasia@gmail.com", "password");
        when(userService.saveUser(user)).thenReturn(null);

        ResponseEntity<?> response = accountController.registerUser(user);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testRegisterUser_Failure() {
        User user = new User("Kasia", "kasia@gmail.com", "password");
        when(userService.saveUser(user)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = accountController.registerUser(user);

        assertEquals(500, response.getStatusCodeValue());
    }
}