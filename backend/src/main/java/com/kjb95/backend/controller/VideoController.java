package com.kjb95.backend.controller;

import com.kjb95.backend.constant.Languages;
import com.kjb95.backend.dto.AddVideoDto;
import com.kjb95.backend.dto.AddVideoDtoList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/video")
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

    @PostMapping("/initial-value")
    public void videoInitialValue(@Validated @RequestBody AddVideoDtoList addVideoDtoList, BindingResult bindingResult) {
        log.info("Post /api/video/initial-value");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return ;
        }
        addVideoDtoList.getAddVideoDtoList().forEach(videoService::addVideo);
    }

    @GetMapping()
    public List<VideoDto> getVideo(@RequestParam("lang") String lang) {
        log.info("Get /api/video");
        if (!languages.getLanguages().contains(lang)) {
            log.error(unsupportedLanguages);
            return null;
        }
        return videoService.getVideo(lang);
    }
    @PostMapping()
    public void addVideo(@Validated @RequestBody AddVideoDto addVideoDto, BindingResult bindingResult) {
        log.info("Post /api/video");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return ;
        }
        videoService.addVideo(addVideoDto);
    }
    @DeleteMapping()
    public boolean deleteVideo(@RequestBody Map<String,Boolean> videoIds) {
        log.info("Delete /api/video");
        videoService.deleteVideo(videoIds);
        return true;
    }

    @GetMapping("/random-value")
    public List<VideoDto> getRandomVideo(@RequestParam("lang") String lang) {
        log.info("Get /api/video/random-value");
        if (!languages.getLanguages().contains(lang)) {
            log.error(unsupportedLanguages);
            return null;
        }
        return videoService.getRandomVideo(lang);
    }
}
