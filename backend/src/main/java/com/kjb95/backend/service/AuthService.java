package com.kjb95.backend.service;

import com.kjb95.backend.constant.ErrorCode;
import com.kjb95.backend.dto.LoginRequestDto;
import com.kjb95.backend.dto.LoginResponseDto;
import com.kjb95.backend.entity.User;
import com.kjb95.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    /**
     * 로그인
     *
     * @param loginRequestDto 로그인 데이터
     * @return 로그인 성공 혹은 실패 정보가 담긴 데이터
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getId())
            .orElse(null);
        if (user == null) {
            return new LoginResponseDto(false, ErrorCode.ID_DOES_NOT_EXIST);
        }
        else if (!user.getPassword()
            .equals(loginRequestDto.getPassword())) {
            return new LoginResponseDto(false, ErrorCode.WRONG_PASSWORD);
        }
        return new LoginResponseDto(true, null);
    }
}
