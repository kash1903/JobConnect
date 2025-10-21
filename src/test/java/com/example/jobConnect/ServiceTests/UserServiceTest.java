package com.example.jobConnect.ServiceTests;

import com.example.jobConnect.model.User;
import com.example.jobConnect.repository.UserRepo;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class UserServiceTest {
      @Test
    public void testRegisterUser_Success() {
        UserRepo userRepo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("12345");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepo.save(user)).thenReturn(user);

       
    }

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        UserRepo userRepo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));


    }

    @Test
    public void testLoginUser_Success() {
        UserRepo userRepo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("12345");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

       
    }

    @Test
    public void testLoginUser_InvalidPassword() {
        UserRepo userRepo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("12345");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

 
    }

    @Test
    public void testGetUserById_Success() {
        UserRepo userRepo = mock(UserRepo.class);
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

      
    }
}
