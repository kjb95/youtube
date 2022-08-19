package com.kjb95.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserDto {

    @NotNull(message = "아이디는 필수 입력값 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "영문자, 숫자만을 포함하여 8~20자로 입력하세요")
    private String id;

    @NotNull(message = "비밀번호는 필수 입력값 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "영문자, 숫자만을 포함하여 8~20자로 입력하세요")
    private String password;

    @NotNull(message = "닉네임은 필수 입력값 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "영문자, 숫자만을 포함하여 8~20자로 입력하세요")
    private String nickname;
}
