package com.kjb95.backend.entity;

import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.utils.UnitCalculator;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

    @Id
    private String id;

    @Column
    private String channelId;
    @Column
    private String channelTitle;
    @Column(length = 10000)
    private String description;
    @Column
    private String title;
    @Column
    private LocalDateTime publishedAt;
    @Column
    private long viewCount;
    @Column
    private long subscriberCount;
    @Column
    private boolean isExist;

    @Override
    public String toString() {
        return "Video{" + "id='" + id + '\'' + ", channelId='" + channelId + '\'' + ", channelTitle='" + channelTitle + '\'' + ", description='" + description + '\'' + ", title='" + title + '\'' + ", publishedAt=" + publishedAt + ", viewCount=" + viewCount + ", subscriberCount=" + subscriberCount + ", isExist=" + isExist + '}';
    }

    /**
     * video를 VideoDtoo로 변환
     *
     * @param language 다국어 설정값
     * @return Video에서 변환된 VideoDto
     */
    public VideoDto toVideoDto(String language) {
        Duration duration = Duration.between(publishedAt, LocalDateTime.now());
        String durationString = UnitCalculator.calculateDurationBySeconds(duration.getSeconds(), language);

        String viewCountString = "";
        String subscriberCountString = "";
        if (language.equals("ko")) {
            viewCountString = UnitCalculator.calculateStringByNumber("조회수 ", viewCount, "회", language);
            subscriberCountString = UnitCalculator.calculateStringByNumber("구독자 ", subscriberCount, "명", language);
        }
        else if (language.equals("en")) {
            viewCountString = UnitCalculator.calculateStringByNumber("", viewCount, " views", language);
            subscriberCountString = UnitCalculator.calculateStringByNumber("", subscriberCount, " subscribers", language);
        }

        VideoDto videoDto = VideoDto.builder()
            .id(id)
            .channelId(channelId)
            .channelTitle(channelTitle)
            .description(description)
            .title(title)
            .publishedAt(durationString)
            .viewCount(viewCountString)
            .subscriberCount(subscriberCountString)
            .isExist(isExist)
            .build();
        return videoDto;
    }
}
