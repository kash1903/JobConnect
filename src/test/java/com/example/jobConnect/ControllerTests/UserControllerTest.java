package com.example.jobConnect.ControllerTests;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import com.example.jobConnect.controller.UserController;
import com.example.jobConnect.model.User;
// import com.example.jobConnect.service.UserService;

public class UserControllerTest {
    
    @Test
    public void testRegisterUser() {
        // create objects manually (no framework magic)
        // UserService userService = new UserService(); 
        UserController controller = new UserController();
        
        

        // create a sample user
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("12345");

        // call the method
        String result = controller.registerUser(user);

        // check output
        assertNotNull(result);
    }

    @Test
    public void testGetUserInfo() {
        // UserService userService = new UserService();
        UserController controller = new UserController();
        // controller.userService = userService;

        User user = new User();
        user.setId(1L);
        user.setName("Alice");

        User result = controller.getUserInfo(1L);
        assertNull(result); // assuming no real data
    }
}
