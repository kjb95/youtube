package com.kjb95.backend.service;

import com.kjb95.backend.dto.CreateVideoRequestDto;
import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.entity.Video;
import com.kjb95.backend.repository.VideoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    /**
     * 동영상 추가
     *
     * @param createVideoRequestDto 추가할 유튜브 비디오
     */
    public void createVideo(CreateVideoRequestDto createVideoRequestDto) {
        Video video = createVideoRequestDto.toVideo();
        log.info("createVideo : {}", video);
        videoRepository.save(video);
    }

    /**
     * 모든 동영상 리스트 조회
     *
     * @param language 다국어 설정값
     * @return 모든 동영상 리스트
     */
    public List<VideoDto> findAllVideo(String language) {
        List<VideoDto> videoDtoList = new ArrayList();
        List<Video> videoList = videoRepository.findAll(Sort.by(Direction.ASC, "publishedAt"));
        for (Video video : videoList) {
            videoDtoList.add(video.toVideoDto(language));
        }
        return videoDtoList;
    }

    /**
     * 동영상을 랜덤으로 섞기
     *
     * @param videoDtoList 랜덤으로 섞을 동영상 리스트
     * @return 랜덤으로 섞은 동영상 리스트
     */
    private List<VideoDto> combineVideoDtoList(List<VideoDto> videoDtoList) {
        for (int i = 0; i < videoDtoList.size(); i++) {
            VideoDto temp = videoDtoList.get(i);
            int randomNumber = (int) (Math.random() * videoDtoList.size());
            videoDtoList.set(i, videoDtoList.get(randomNumber));
            videoDtoList.set(randomNumber, temp);
        }
        return videoDtoList;
    }

    /**
     * 랜덤으로 섞은 동영상 리스트 조회
     *
     * @param language 다국어 설정값
     * @return 랜덤으로 섞은 동영상 리스트
     */
    public List<VideoDto> findRandomVideo(String language) {
        List<VideoDto> videoDtoList = new ArrayList();
        videoRepository.findAll()
            .forEach(video -> videoDtoList.add(video.toVideoDto(language)));
        return combineVideoDtoList(videoDtoList);
    }

    /**
     * 동영상 삭제
     *
     * @param videoIds 삭제할 유튜브 비디오 아이디들이 담긴 map
     */
    public void deleteVideoByIdList(@RequestBody Map<String, Boolean> videoIds) {
        videoRepository.findAll()
            .forEach(video -> {
                if (videoIds.get(video.getId()) == null || videoIds.get(video.getId()) == false) {
                    return;
                }

                log.info("deleteVideoByIdList : {}", video);
                video.setExist(false);
                videoRepository.save(video);
            });
    }
}
