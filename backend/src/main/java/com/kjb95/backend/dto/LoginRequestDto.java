package com.kjb95.backend.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRequestDto {

    @NotNull(message = "아이디는 필수 입력값 입니다.")
    private String id;

    private String password;
}
