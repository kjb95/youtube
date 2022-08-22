package com.kjb95.backend.service;

import com.kjb95.backend.dto.AddUserDto;
import com.kjb95.backend.dto.AddUserResponseDto;
import com.kjb95.backend.entity.User;
import com.kjb95.backend.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource(value = "classpath:static/errors.properties")
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("${overlap-id}")
    private String overlapID;

    /**
     * 회원가입
     *
     * @param addUserDto 회원가입할 유저 정보
     * @return 회원가입 성공 혹은 실패 정보가 담긴 데이터
     */
    public AddUserResponseDto addUser(AddUserDto addUserDto) {
        if (userRepository.findById(addUserDto.getId())
            .orElse(null) != null) {
            List<String> errorMessageList = List.of(overlapID);
            log.error("errors={}", overlapID);
            AddUserResponseDto addUserResponseDto = AddUserResponseDto.builder()
                .isSuccess(false)
                .errorMessageList(errorMessageList)
                .build();
            return addUserResponseDto;
        }

        User user = User.builder()
            .id(addUserDto.getId())
            .password(addUserDto.getPassword())
            .nickname(addUserDto.getNickname())
            .build();
        log.info("addUser : {}", user.toString());
        userRepository.save(user);
        return new AddUserResponseDto();
    }
}
