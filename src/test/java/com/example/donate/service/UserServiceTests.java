package com.example.donate.service;


import com.example.donate.model.User;
import com.example.donate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void testEmailUniqueReturnDuplicate() {
        String email = "test@test.oom";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepository.getUserByUserName(email)).thenReturn(user);

        String result = userService.uniqueEmail(email);
        assertThat(result).isEqualTo("DUPLICATE");
    }

    @Test
    public void testUniqueEmailReturnOK() {
        String email = "abc@gmail.com";

        Mockito.when(userRepository.getUserByUserName(email)).thenReturn(null);

        String result = userService.uniqueEmail(email);
        assertThat(result).isEqualTo("OK");
    }



}
