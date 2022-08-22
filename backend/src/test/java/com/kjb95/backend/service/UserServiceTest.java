package com.kjb95.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kjb95.backend.dto.AddUserDto;
import com.kjb95.backend.dto.AddUserResponseDto;
import com.kjb95.backend.entity.User;
import com.kjb95.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        User user = User.builder()
            .id("11111111")
            .nickname("22222222")
            .nickname("33333333")
            .build();
        userRepository.save(user);
    }

    @Test
    @DisplayName("유저 생성시 동일한 아이디를 가진 사람이 있으면 안됨")
    public void addUser() {
        AddUserDto addUserDto = AddUserDto.builder()
            .id("11111111")
            .nickname("22222222")
            .nickname("33333333")
            .build();
        AddUserResponseDto addUserResponseDto = userService.addUser(addUserDto);

        assertThat(addUserResponseDto.isSuccess()).isEqualTo(false);
    }
}