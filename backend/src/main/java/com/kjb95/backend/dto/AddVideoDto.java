package com.kjb95.backend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class AddVideoDto {

    @NotNull(message = "아이디는 필수 입력값 입니다.")
    private String id;
    @NotNull(message = "채널 아이디는 필수 입력값 입니다.")
    private String channelId;
    @NotNull(message = "채널 제목은 필수 입력값 입니다.")
    private String channelTitle;
    @NotNull(message = "설명은 필수 입력값 입니다.")
    private String description;
    @NotNull(message = "제목은 필수 입력값 입니다.")
    private String title;
    @NotNull(message = "날짜는 필수 입력값 입니다.")
    private String publishedAt;
    @Min(value = 0, message = "조회수는 0 이상 이어야 합니다.")
    @NotNull(message = "조회수는 필수 입력값 입니다.")
    private long viewCount;
    @Min(value = 0, message = "구독자 수는 0 이상 이어야 합니다.")
    private Long subscriberCount;
}
