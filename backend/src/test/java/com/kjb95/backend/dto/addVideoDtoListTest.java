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
public class addVideoDtoListTest {

    @Test
    @DisplayName("AddVideoDtoList @Validated 유효성 검사")
    public void addVideoDtoList() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        AddVideoDto addVideoDto = AddVideoDto.builder()
            .id("EVDWHCOlbOw")
            .channelId("UCEEGx5rpyzmcukyZmqr-MnA")
            .channelTitle("알앤비박사장")
            .description("아무말 설명")
            .title("박재범")
//            .publishedAt("2022-07-28T02:52:58Z")
            .viewCount(-2)
            .subscriberCount(Long.valueOf(123456))
            .build();

        List<AddVideoDto> addVideoDtoList = new ArrayList<>();
        addVideoDtoList.add(addVideoDto);
        AddVideoDtoList list = new AddVideoDtoList(addVideoDtoList);

        Set<ConstraintViolation<AddVideoDtoList>> validate = validator.validate(list);
        Iterator<ConstraintViolation<AddVideoDtoList>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<AddVideoDtoList> next = iterator.next();
            messages.add(next.getMessage());
        }
        org.assertj.core.api.Assertions.assertThat(messages)
            .contains("날짜는 필수 입력값 입니다.", "조회수는 0 이상 이어야 합니다.");
    }
}
