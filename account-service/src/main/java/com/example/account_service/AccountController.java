
package com.example.account_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/register")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("Registering user: " + user.getName() + ", " + user.getEmail() + ", " + user.getPassword());

        // Save user in account service database
        userService.saveUser(user);

        String jsonUser = UserFactory.getUserAsJSONString(user);
        boolean isUserCreated = postNewUserToCustomerAPI(jsonUser);

        if (!isUserCreated) {
            return ResponseEntity.status(500).body("User registration failed");
        }

        return ResponseEntity.ok("User registered successfully");
    }

    private boolean postNewUserToCustomerAPI(String jsonUser) {
        try {
            URL url = new URL("http://localhost:8080/api/customers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Użycie tokena, jeżeli wymagana jest autoryzacja JWT
            // conn.setRequestProperty("Authorization", "Bearer " + jwtToken);

            OutputStream os = conn.getOutputStream();
            os.write(jsonUser.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("Failed: HTTP error code : " + conn.getResponseCode());
                return false;
            }

            conn.disconnect();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

//package com.example.account_service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/register")
//public class AccountController {
//    @Autowired
//    private UserService userService;
//
////    @PostMapping("/register")
////    public String register(@RequestBody User user){
////        System.out.println("Rejestracja uzytkownika: " + user.getName() + ", "+ user.getEmail() + ", "+ user.getPassword());
////        userService.saveUser(user);
////        return "User registered succesfully";
////    }
//    @PostMapping
//    public ResponseEntity<?> registerUser(@RequestBody User user){
//        System.out.println("Registering user: " + user.getName() + ", "+ user.getEmail() + ", "+ user.getPassword());
//        userService.saveUser(user);
//        return ResponseEntity.ok("User registered succesfully");
//}
//
//    @PostMapping("/token")
//    public String generateToken(@RequestBody AuthRequest authRequest){
//        return userService.generateJwtToken(authRequest.getEmail(), authRequest.getPassword());
//    }
//
//}

