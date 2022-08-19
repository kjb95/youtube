package com.kjb95.backend.controller;

import com.kjb95.backend.dto.AddUserDto;
import com.kjb95.backend.dto.AddUserResponseDto;
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
@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public AddUserResponseDto addUser(@Validated @RequestBody AddUserDto addUserDto, BindingResult bindingResult) {
        log.info("Post /api/users");
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult.getAllErrors());
            List<String> errorMessageList = bindingResult
                .getAllErrors().stream()
                .map(s -> s.getDefaultMessage())
                .collect(Collectors.toList());
            AddUserResponseDto addUserResponseDto = AddUserResponseDto.builder()
                .isSuccess(false)
                .errorMessageList(errorMessageList)
                .build();
            return addUserResponseDto;
        }
        return userService.addUser(addUserDto);
    }
}
