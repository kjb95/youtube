package com.kjb95.backend.service;

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
    Map<String,Object> playlistWithoutSubscriberCount = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        Video video = videoRepository.findById("EVDWHCOlbOw");
        if (video != null)
            videoRepository.delete(video);
        playlistWithoutSubscriberCount.put("id","EVDWHCOlbOw");
        playlistWithoutSubscriberCount.put("channelId","UCEEGx5rpyzmcukyZmqr-MnA");
        playlistWithoutSubscriberCount.put("channelTitle","알앤비박사장");
        playlistWithoutSubscriberCount.put("description","아무말 설명2");
        playlistWithoutSubscriberCount.put("title","박재범");
        playlistWithoutSubscriberCount.put("publishedAt","2022-07-28T02:52:58Z");
        playlistWithoutSubscriberCount.put("viewCount","1364");
        playlistWithoutSubscriberCount.put("exist","true");
    }

    @Test
    @DisplayName("구독자수 비공개면 구독자 수는 0명 이하")
    public void addPlaylistTest() {
        videoService.addPlaylist(playlistWithoutSubscriberCount);
        Video video = videoRepository.findById((String)playlistWithoutSubscriberCount.get("id"));
        assertThat(video.getSubscriberCount()).isLessThan(0);
    }

    @Test
    @DisplayName("플레이리스트 삭제")
    public void deletePlaylist() {
        videoService.addPlaylist(playlistWithoutSubscriberCount);
        String id = (String)playlistWithoutSubscriberCount.get("id");
        Video video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(true);

        Map<String,Object> playlistIds = new HashMap<>();
        playlistIds.put(id, true);
        videoService.deletePlaylist(playlistIds);
        video = videoRepository.findById(id);
        assertThat(video.isExist()).isEqualTo(false);
    }
}
