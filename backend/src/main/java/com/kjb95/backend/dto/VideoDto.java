package com.kjb95.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDto {
    private String id;
    private String channelId;
    private String channelTitle;
    private String description;
    private String title;
    private String publishedAt;
    private String viewCount;
    private String subscriberCount;
    private boolean isExist;
}
