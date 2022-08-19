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
public class AddUserResponseDto {

    private boolean isSuccess;
    private List<String> errorMessageList;

    public AddUserResponseDto() {
        isSuccess = true;
        errorMessageList = null;
    }
}
