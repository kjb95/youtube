package com.kjb95.backend.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddVideoDtoList {
    @NotNull
    @Valid
    private List<AddVideoDto> addVideoDtoList;
}
