package com.kjb95.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kjb95.backend.constant.ErrorCode;
import com.kjb95.backend.dto.LoginRequestDto;
import com.kjb95.backend.dto.LoginResponseDto;
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
public class AuthServiceTest {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        User user = User.builder()
            .id("11111111")
            .password("22222222")
            .nickname("33333333")
            .build();
        userRepository.save(user);
    }

    @Test
    @DisplayName("존재하는 아이디가 없을 경우")
    public void login() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
            .id("98769876")
            .password("98769876")
            .build();

        LoginResponseDto login = authService.login(loginRequestDto);
        assertThat(login.getErrorMessage()).isEqualTo(ErrorCode.ID_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않을 경우")
    public void login2() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
            .id("11111111")
            .password("11111111")
            .build();

        LoginResponseDto login = authService.login(loginRequestDto);
        assertThat(login.getErrorMessage()).isEqualTo(ErrorCode.WRONG_PASSWORD);
    }
}
