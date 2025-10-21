package com.example.jobConnect.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.jobConnect.model.User;
import com.example.jobConnect.repository.UserRepo;
import com.example.jobConnect.service.UserService;

public class UserRepoTest {
       @Test
    public void testRegisterUser_Success() {
        UserRepo repo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test");

        when(repo.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(repo.save(user)).thenReturn(user);

        UserService service = new UserService();
        // service.userRepo = repo;

        User saved = service.registerUser(user);
        assertEquals("Test", saved.getName());
    }

    @Test
    public void testRegisterUser_EmailExists() {
        UserRepo repo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");

        when(repo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        UserService service = new UserService();
        // service.userRepo = repo;

        Exception exception = assertThrows(RuntimeException.class, () -> service.registerUser(user));
        assertEquals("Email already registered!", exception.getMessage());
    }

    @Test
    public void testLoginUser_Success() {
        UserRepo repo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("pass123");

        when(repo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        UserService service = new UserService();
        // service.userRepo = repo;

        User loggedIn = service.loginUser("test@example.com", "pass123");
        assertEquals("test@example.com", loggedIn.getEmail());
    }

    @Test
    public void testLoginUser_InvalidPassword() {
        UserRepo repo = mock(UserRepo.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("pass123");

        when(repo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        UserService service = new UserService();
        // service.userRepo = repo;

        Exception exception = assertThrows(RuntimeException.class, () -> 
            service.loginUser("test@example.com", "wrongpass")
        );
        assertEquals("Invalid password!", exception.getMessage());
    }

    @Test
    public void testGetUserById() {
        UserRepo repo = mock(UserRepo.class);
        User user = new User();
        user.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(user));

        UserService service = new UserService();
        // service.userRepo = repo;

        User found = service.getUserById(1L);
        assertEquals(1L, found.getId());
    }
}
