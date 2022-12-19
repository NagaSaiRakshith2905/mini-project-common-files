package com.capgemini.login.service;

import com.capgemini.login.exception.UserAlreadyExistsException;
import com.capgemini.login.exception.UserNotFoundException;
import com.capgemini.login.model.User;
import com.capgemini.login.pojo.UserRequest;
import com.capgemini.login.pojo.UserResponse;
import com.capgemini.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class UserServiceTest {


    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    private String username1 = "User1";
    private String email1 = "user1@gmail.com";
    private String password1 = "password1";
    private User user1 = User.builder()
            .userName(username1)
            .email(email1)
            .password(password1)
            .build();
    private UserRequest userRequest1 = UserRequest.builder().userName(username1).email(email1).password(password1).build();

    private String username2 = "User2";
    private String email2 = "user2@gmail.com";
    private String password2= "password2";
    private User user2 = User.builder()
            .userName(username2)
            .email(email2)
            .password(password2)
            .build();
    private UserRequest userRequest2 = UserRequest.builder().userName(username2).email(email2).password(password2).build();

    @Test
    void registerUserTest(){
        when(userRepository.findUserByUserName(username1)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email1)).thenReturn(Optional.empty());
        when(userRepository.save(user1)).thenReturn(user1);
        String usernameResponse = userService.registerUser(userRequest1);
        assertThat(usernameResponse).isEqualTo(user1.getUserName());

        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.of(user2));
        assertThrows(UserAlreadyExistsException.class,()->userService.registerUser(userRequest2));

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.of(user2));
        assertThrows(UserAlreadyExistsException.class,()->userService.registerUser(userRequest2));

    }

    @Test
    void loginUserTest(){
        when(userRepository.findUserByUserName(username1)).thenReturn(Optional.of(user1));
        String usernameResponse1 = userService.loginUser(username1, password1);
        assertThat(usernameResponse1).isEqualTo(username1);

        when(userRepository.findUserByUserName(username1)).thenReturn(Optional.of(user1));
        assertThrows(UserNotFoundException.class,()->userService.loginUser(username1, password2));

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.of(user2));
        String usernameResponse2 = userService.loginUser(email2, password2);
        assertThat(usernameResponse2).isEqualTo(username2);

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.of(user2));
        assertThrows(UserNotFoundException.class,()->userService.loginUser(username2, password1));

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.loginUser(username2, password1));
    }

    @Test
    void getUserByUserNameTest(){
        when(userRepository.findUserByUserName(username1)).thenReturn(Optional.of(user1));
        UserResponse userResponse = userService.getUserByUserName(username1);
        assertThat(userResponse).isEqualTo(userResponse);

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.of(user2));
        assertThrows(UserNotFoundException.class,()->userService.getUserByUserName("user_3"));

    }

    @Test
    void getUserByIdTest(){
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        UserResponse userResponse = userService.getUserById(1);
        assertThat(userResponse).isEqualTo(userResponse);

        when(userRepository.findById(2)).thenReturn(Optional.of(user2));
        assertThrows(UserNotFoundException.class,()->userService.getUserById(3));

    }

    @Test
    void updatePasswordTest(){
        when(userRepository.findUserByUserName(username1)).thenReturn(Optional.of(user1));
        String usernameResponse1 = userService.updatePassword(username1, password1);
        assertThat(usernameResponse1).isEqualTo("password Updated successFully");

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.of(user2));
        String usernameResponse2 = userService.updatePassword(email2, password2);
        assertThat(usernameResponse2).isEqualTo("password Updated successFully");

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.of(user2));
        assertThrows(UserNotFoundException.class,()->userService.updatePassword(username2, password1));

        when(userRepository.findUserByUserName(username2)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(email2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.updatePassword(username2, password1));
    }
}
