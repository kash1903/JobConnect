package com.example.jobConnect.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.mock.web.MockHttpSession;

import com.example.jobConnect.controller.LandingController;

public class LandingControllerTest {
    

      LandingController controller = new LandingController();

    @Test
    public void testLandingPage() {
        String viewName = controller.landingPage();
        assertEquals("index", viewName);
    }

    @Test
    public void testShowRegisterPage() {
        String viewName = controller.showRegisterPage();
        assertEquals("register", viewName);
    }

    @Test
    public void testShowLoginPage() {
        String viewName = controller.showLoginPage();
        assertEquals("login", viewName);
    }

    @Test
    public void testHandleRegistration() {
        // Mock model
        Model model = new org.springframework.validation.support.BindingAwareModelMap();

        String result = controller.handleRegistration(
                "Test User", "test@example.com", "12345", "JOB_SEEKER", model);

        assertEquals("index", result);
    }

    @Test
    public void testJobSeekerPage() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", 1L);
        session.setAttribute("userName", "Test");
        session.setAttribute("userEmail", "test@example.com");

        Model model = new org.springframework.validation.support.BindingAwareModelMap();
        String result = controller.jobSeekerPage(session, model);

        assertEquals("jobseeker", result);
        assertEquals("Test", model.getAttribute("userName"));
    }

    @Test
    public void testLogout() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userName", "Test");

        String result = controller.logout(session);

        assertEquals("redirect:/login", result);
        assertNull(session.getAttribute("userName"));
    }
}
