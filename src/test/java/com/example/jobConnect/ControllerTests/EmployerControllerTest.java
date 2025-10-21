package com.example.jobConnect.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.jobConnect.controller.EmployerController;

import jakarta.servlet.http.HttpSession;

@WebMvcTest(EmployerController.class)
public class EmployerControllerTest {


     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpSession session; // Mocking HttpSession for isolation

    @Test
    void testEmployerDashboard_ReturnsEmployerView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employer"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200 OK
                .andExpect(MockMvcResultMatchers.view().name("employer")); // View name matches
    }

    
}
