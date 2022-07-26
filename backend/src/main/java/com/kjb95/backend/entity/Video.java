package com.kjb95.backend.entity;

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
}
