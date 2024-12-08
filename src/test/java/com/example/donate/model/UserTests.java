package com.example.donate.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {
    @Test
    public void testName() {
        User user = new User();
        String name = "Ali Raza";
        user.setName(name);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void testEmail() {
        User user = new User();
        String email = "jawwadhussain0@gmail.com";
        user.setEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void testPassword() {
        User user = new User();
        String password = "test123";
        user.setPassword(password);

        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void testRole() {
        User user = new User();
        String role = "GENERAL";
        user.setRole(role);

        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    public void testAddress() {
        User user = new User();
        String address = "Mehran University Jamshoro";
        user.setAddress(address);

        assertThat(user.getAddress()).isEqualTo(address);
    }
}
