package com.kjb95.backend.controller;

import com.kjb95.backend.constant.ErrorCode;
import com.kjb95.backend.dto.LoginRequestDto;
import com.kjb95.backend.dto.LoginResponseDto;
import com.kjb95.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인
     *
     * @param loginRequestDto 로그인 데이터
     * @return 로그인 성공 혹은 실패 정보가 담긴 데이터
     */
    @PostMapping("/login")
    public LoginResponseDto login(@Validated @RequestBody LoginRequestDto loginRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return new LoginResponseDto(false, ErrorCode.NOT_NULL_ID);
        }
        return authService.login(loginRequestDto);
    }
}
