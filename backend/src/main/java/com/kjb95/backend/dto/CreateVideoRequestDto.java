package com.kjb95.backend.dto;

import com.kjb95.backend.entity.Video;
import java.time.LocalDateTime;
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
public class CreateVideoRequestDto {

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

    /**
     * createVideoDto를 Video로 변환
     *
     * @return 변환된 Video
     */
    public Video toVideo() {
        String publishedAtStr = publishedAt;
        publishedAtStr = publishedAtStr.substring(0, publishedAtStr.length() - 1);
        LocalDateTime publishedAt = LocalDateTime.parse(publishedAtStr);

        Video video = Video.builder()
            .id(id)
            .channelId(channelId)
            .channelTitle(channelTitle)
            .description(description)
            .title(title)
            .publishedAt(publishedAt)
            .viewCount(viewCount)
            .isExist(true)
            .build();
        if (subscriberCount == null) {
            video.setSubscriberCount(-1);
        }
        else {
            video.setSubscriberCount(subscriberCount);
        }
        return video;
    }
}
