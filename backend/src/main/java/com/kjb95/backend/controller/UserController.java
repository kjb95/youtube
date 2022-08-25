package com.kjb95.backend.controller;

import com.kjb95.backend.dto.CreateUserRequestDto;
import com.kjb95.backend.dto.CreateUserResponseDto;
import com.kjb95.backend.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     *
     * @param createUserRequestDto 회원가입할 유저 정보
     * @return 회원가입 성공 혹은 실패 정보가 담긴 데이터
     */
    @PostMapping
    public CreateUserResponseDto createUser(@Validated @RequestBody CreateUserRequestDto createUserRequestDto, BindingResult bindingResult) {
        log.info("Post /api/users");
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult.getAllErrors());
            List<String> errorMessageList = bindingResult.getAllErrors()
                .stream()
                .map(s -> s.getDefaultMessage())
                .collect(Collectors.toList());
            CreateUserResponseDto createUserResponseDto = CreateUserResponseDto.builder()
                .isSuccess(false)
                .errorMessageList(errorMessageList)
                .build();
            return createUserResponseDto;
        }
        return userService.createUser(createUserRequestDto);
    }
}
