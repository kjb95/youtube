package com.kjb95.backend.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
public class createVideoDtoListTest {

    @Test
    @DisplayName("CreateVideoDtoList @Validated 유효성 검사")
    public void createVideoDtoList() {
        Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

        CreateVideoRequestDto createVideoRequestDto = CreateVideoRequestDto.builder()
            .id("EVDWHCOlbOw")
            .channelId("UCEEGx5rpyzmcukyZmqr-MnA")
            .channelTitle("알앤비박사장")
            .description("아무말 설명")
            .title("박재범")
            //            .publishedAt("2022-07-28T02:52:58Z")
            .viewCount(-2)
            .subscriberCount(Long.valueOf(123456))
            .build();

        List<CreateVideoRequestDto> createVideoRequestDtoList = new ArrayList<>();
        createVideoRequestDtoList.add(createVideoRequestDto);
        CreateVideoDtoList list = new CreateVideoDtoList(createVideoRequestDtoList);

        Set<ConstraintViolation<CreateVideoDtoList>> validate = validator.validate(list);
        Iterator<ConstraintViolation<CreateVideoDtoList>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<CreateVideoDtoList> next = iterator.next();
            messages.add(next.getMessage());
        }
        org.assertj.core.api.Assertions.assertThat(messages)
            .contains("날짜는 필수 입력값 입니다.", "조회수는 0 이상 이어야 합니다.");
    }
}
