package com.kjb95.backend.controller;

import com.kjb95.backend.constant.Languages;
import com.kjb95.backend.dto.AddPlaylistDto;
import com.kjb95.backend.dto.AddPlaylistDtoList;
import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.service.VideoService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@PropertySource(value="classpath:static/errors.properties")
@Slf4j
@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final Languages languages;

    @Value("${unsupported-languages}")
    private String unsupportedLanguages;

    @PostMapping("/api/playlist/initial-value")
    public void playlistInitialValue(@Validated @RequestBody AddPlaylistDtoList playlists, BindingResult bindingResult) {
        log.info("Post /api/playlist/initial-value");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return ;
        }
        playlists.getAddPlaylistDtoList().forEach(videoService::addPlaylist);
    }

    @GetMapping("/api/playlist")
    public List<VideoDto> getPlaylist(@RequestParam("lang") String lang) {
        log.info("Get /api/playlist");
        if (!languages.getLanguages().contains(lang)) {
            log.error(unsupportedLanguages);
            return null;
        }
        return this.videoService.getPlaylist(lang);
    }
    @PostMapping("/api/playlist")
    public void addPlaylist(@Validated @RequestBody AddPlaylistDto playlist, BindingResult bindingResult) {
        log.info("Post /api/playlist");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return ;
        }
        videoService.addPlaylist(playlist);
    }
    @DeleteMapping("/api/playlist")
    public boolean deletePlaylist(@RequestBody Map<String,Boolean> playlistIds) {
        log.info("Delete /api/playlist");
        videoService.deletePlaylist(playlistIds);
        return true;
    }

    @GetMapping("/api/playlist/random")
    public List<VideoDto> getRandomPlaylist(@RequestParam("lang") String lang) {
        log.info("Get /api/playlist/random");
        if (!languages.getLanguages().contains(lang)) {
            log.error(unsupportedLanguages);
            return null;
        }
        return this.videoService.getRandomPlaylist(lang);
    }
}
