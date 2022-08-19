package com.kjb95.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kjb95.backend.dto.AddVideoDto;
import com.kjb95.backend.entity.Video;
import com.kjb95.backend.repository.VideoRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;
    @Autowired
    VideoRepository videoRepository;
    AddVideoDto videoWithoutSubscriberCount;

    @BeforeEach
    public void beforeEach() {
        Video video = videoRepository.findById("EVDWHCOlbOw");
        if (video != null) {
            videoRepository.delete(video);
        }

        videoWithoutSubscriberCount = AddVideoDto.builder()
            .id("EVDWHCOlbOw")
            .channelId("UCEEGx5rpyzmcukyZmqr-MnA")
            .channelTitle("알앤비박사장")
            .title("박재범")
            .publishedAt("2022-07-28T02:52:58Z")
            .viewCount(1234)
            .build();
    }

    @Test
    @DisplayName("구독자수 비공개면 구독자 수는 0명 이하")
    public void addVideo() {
        videoService.addVideo(videoWithoutSubscriberCount);
        Video video = videoRepository.findById(videoWithoutSubscriberCount.getId());
        assertThat(video.getSubscriberCount()).isLessThan(0);
    }

    @Test
    @DisplayName("플레이리스트 삭제")
    public void deleteVideo() {
        videoService.addVideo(videoWithoutSubscriberCount);
        String id = videoWithoutSubscriberCount.getId();
        Video video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(true);

        Map<String, Boolean> videoIds = new HashMap<>();
        videoIds.put(id, true);
        videoService.deleteVideo(videoIds);
        video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(false);
    }
}
