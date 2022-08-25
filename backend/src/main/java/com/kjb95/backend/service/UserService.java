package com.kjb95.backend.service;

import com.kjb95.backend.dto.CreateUserRequestDto;
import com.kjb95.backend.dto.CreateUserResponseDto;
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

    @Value("${duplicated-id}")
    private String duplicatedID;

    /**
     * 회원가입
     *
     * @param createUserRequestDto 회원가입할 유저 정보
     * @return 회원가입 성공 혹은 실패 정보가 담긴 데이터
     */
    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.findById(createUserRequestDto.getId())
            .orElse(null) != null) {
            List<String> errorMessageList = List.of(duplicatedID);
            log.error("errors={}", duplicatedID);
            CreateUserResponseDto createUserResponseDto = CreateUserResponseDto.builder()
                .isSuccess(false)
                .errorMessageList(errorMessageList)
                .build();
            return createUserResponseDto;
        }

        User user = User.builder()
            .id(createUserRequestDto.getId())
            .password(createUserRequestDto.getPassword())
            .nickname(createUserRequestDto.getNickname())
            .build();
        log.info("createUser : {}", user.toString());
        userRepository.save(user);
        return new CreateUserResponseDto();
    }
}
