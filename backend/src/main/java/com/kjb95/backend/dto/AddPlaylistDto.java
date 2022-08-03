package com.kjb95.backend.dto;

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
public class AddPlaylistDto {
    private String id;
    private String channelId;
    private String channelTitle;
    private String description;
    private String title;
    private String publishedAt;
    private long viewCount;
    private Long subscriberCount;
    private boolean isExist;
}
