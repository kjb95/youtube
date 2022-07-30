package com.kjb95.backend.controller;

import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.service.VideoService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping("/api/playlist/initial-value")
    public void playlistInitialValue(@RequestBody Map<String,Object>[] playlist) {
        log.info("Post /api/playlist/initial-value");
        Arrays.stream(playlist).forEach(videoService::addPlaylist);
    }

    @GetMapping("/api/playlist")
    public List<VideoDto> getPlaylist() {
        log.info("Get /api/playlist");
        return this.videoService.getPlaylist();
    }
    @PostMapping("/api/playlist")
    public void addPlaylist(@RequestBody Map<String,Object> playlist) {
        log.info("Post /api/playlist");
        videoService.addPlaylist(playlist);
    }
    @DeleteMapping("/api/playlist")
    public boolean deletePlaylist(@RequestBody Map<String,Object> playlistIds) {
        log.info("Delete /api/playlist");
        videoService.deletePlaylist(playlistIds);
        return true;
    }

    @GetMapping("/api/playlist/random")
    public List<VideoDto> getRandomPlaylist() {
        log.info("Get /api/playlist/random");
        return this.videoService.getRandomPlaylist();
    }

}
