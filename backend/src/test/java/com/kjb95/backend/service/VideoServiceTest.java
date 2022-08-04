package com.kjb95.backend.service;

import com.kjb95.backend.dto.AddPlaylistDto;
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

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@SpringBootTest
class VideoServiceTest {
    @Autowired
    VideoService videoService;
    @Autowired
    VideoRepository videoRepository;
    AddPlaylistDto playlistWithoutSubscriberCount;

    @BeforeEach
    public void beforeEach() {
        Video video = videoRepository.findById("EVDWHCOlbOw");
        if (video != null)
            videoRepository.delete(video);

        playlistWithoutSubscriberCount = AddPlaylistDto.builder()
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
    public void addPlaylistTest() {
        videoService.addPlaylist(playlistWithoutSubscriberCount);
        Video video = videoRepository.findById(playlistWithoutSubscriberCount.getId());
        assertThat(video.getSubscriberCount()).isLessThan(0);
    }

    @Test
    @DisplayName("플레이리스트 삭제")
    public void deletePlaylist() {
        videoService.addPlaylist(playlistWithoutSubscriberCount);
        String id = playlistWithoutSubscriberCount.getId();
        Video video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(true);

        Map<String,Boolean> playlistIds = new HashMap<>();
        playlistIds.put(id, true);
        videoService.deletePlaylist(playlistIds);
        video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(false);
    }
}
