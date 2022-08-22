package com.kjb95.backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {

    private boolean isSuccess;
    private List<String> errorMessageList;

    public CreateUserResponseDto() {
        isSuccess = true;
        errorMessageList = null;
    }
}
