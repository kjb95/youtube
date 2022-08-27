package com.kjb95.backend.controller;

import com.kjb95.backend.constant.ConstantString;
import com.kjb95.backend.constant.Languages;
import com.kjb95.backend.dto.CreateVideoDtoList;
import com.kjb95.backend.dto.CreateVideoRequestDto;
import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.service.VideoService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final Languages languages;

    /**
     * 디비에 동영상 리스트 초기값 저장
     *
     * @param createVideoDtoList 저장할 동영상 리스트
     */
    @PostMapping("/initial-value")
    public void initVideo(@Validated @RequestBody CreateVideoDtoList createVideoDtoList, BindingResult bindingResult) {
        log.info("Post /api/video/initial-value");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return;
        }
        createVideoDtoList.getCreateVideoRequestDtoList()
            .forEach(videoService::createVideo);
    }

    /**
     * 동영상 리스트 조회
     *
     * @param language 다국어 설정값
     * @return 동영상 리스트
     */
    @GetMapping()
    public List<VideoDto> findAllVideo(@RequestParam("language") String language) {
        log.info("Get /api/video");
        if (!languages.getLanguages()
            .contains(language)) {
            log.error(ConstantString.UNSUPPORTED_LANGUAGES);
            return null;
        }
        return videoService.findAllVideo(language);
    }

    /**
     * 동영상 추가
     *
     * @param createVideoRequestDto 추가할 동영상
     */
    @PostMapping()
    public void createVideo(@Validated @RequestBody CreateVideoRequestDto createVideoRequestDto, BindingResult bindingResult) {
        log.info("Post /api/video");
        if (bindingResult.hasErrors()) {
            log.error("erros={}", bindingResult.getAllErrors());
            return;
        }
        videoService.createVideo(createVideoRequestDto);
    }

    /**
     * 동영상 삭제
     *
     * @param videoIds 삭제할 동영상 아이디들이 담긴 map
     */
    @DeleteMapping()
    public void deleteVideoByIdList(@RequestBody Map<String, Boolean> videoIds) {
        log.info("Delete /api/video");
        videoService.deleteVideoByIdList(videoIds);
    }

    /**
     * 랜덤으로 동영상 리스트 조회
     *
     * @param language 다국어 설정값
     * @return 랜덤으로 섞은 동영상 리스트
     */
    @GetMapping("/random-value")
    public List<VideoDto> findRandomVideo(@RequestParam("language") String language) {
        log.info("Get /api/video/random-value");
        if (!languages.getLanguages()
            .contains(language)) {
            log.error(ConstantString.UNSUPPORTED_LANGUAGES);
            return null;
        }
        return videoService.findRandomVideo(language);
    }
}
