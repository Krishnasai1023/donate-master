package com.example.donate.repository;

import com.example.donate.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)  //Will not rollback changes
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        String email = "devmail1.ar@gmail.com";
        user.setEmail(email);
        user.setRole("AUTHORIZED");
        user.setEnabled(true);
        user.setPassword("$2a$12$XzN2CB5iozwjOEzqGWj9IuFbhEW26OVRjsAjEWhN/tAhID4FpWH3.");
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getEmail()).isEqualTo(email);
    }

    @Test
    public void testListAllUser() {
        Iterable<User> listOfUser = userRepository.findAll();
        listOfUser.forEach(user -> System.out.println(user));
        assertThat(listOfUser.iterator().hasNext()).isEqualTo(true);
    }

    @Test
    public void testGetUserById() {
        User user = userRepository.findById(1).get();
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User user = userRepository.findById(1).get();
        user.setEnabled(true);
        user.setEmail("devmail.ar@gmail.com");

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getEmail()).isEqualTo("devmail.ar@gmail.com");
        assertThat(savedUser.isEnabled()).isEqualTo(true);

    }

    @Test
    public void testGetUserByUserName() {
        String email = "devmail.ar@gmail.com";
        User user = userRepository.getUserByUserName(email);

        assertThat(user).isNotNull();
    }


}
