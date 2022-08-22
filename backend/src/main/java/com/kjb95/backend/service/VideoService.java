package com.kjb95.backend.service;

import com.kjb95.backend.dto.AddVideoDto;
import com.kjb95.backend.dto.VideoDto;
import com.kjb95.backend.entity.Video;
import com.kjb95.backend.repository.VideoRepository;
import com.kjb95.backend.utils.Calculator;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * @param addVideoDto 추가할 유튜브 비디오
     */
    public void addVideo(AddVideoDto addVideoDto) {
        Video video = addVideoDtoToVideo(addVideoDto);
        log.info("addVideo : {}", video);
        videoRepository.save(video);
    }

    /**
     * addVideoDto를 Video로 변환
     *
     * @param addVideoDto Video로 변환될 AddVideoDto
     * @return AddVideoDto 에서 변환된 Video
     */
    private Video addVideoDtoToVideo(AddVideoDto addVideoDto) {
        String publishedAtStr = addVideoDto.getPublishedAt();
        publishedAtStr = publishedAtStr.substring(0, publishedAtStr.length() - 1);
        LocalDateTime publishedAt = LocalDateTime.parse(publishedAtStr);

        Video video = Video.builder()
            .id(addVideoDto.getId())
            .channelId(addVideoDto.getChannelId())
            .channelTitle(addVideoDto.getChannelTitle())
            .description(addVideoDto.getDescription())
            .title(addVideoDto.getTitle())
            .publishedAt(publishedAt)
            .viewCount(addVideoDto.getViewCount())
            .isExist(true)
            .build();
        if (addVideoDto.getSubscriberCount() == null)
            video.setSubscriberCount(-1);
        else
            video.setSubscriberCount(addVideoDto.getSubscriberCount());

        return video;
    }

    /**
     * video를 VideoDtoo로 변환
     *
     * @param video VideoDto로 변환될 Video
     * @param lang  다국어 설정값
     * @return Video에서 변환된 VideoDto
     */
    private VideoDto videoToVideoDto(Video video, String lang) {
        Duration duration = Duration.between(video.getPublishedAt(), LocalDateTime.now());
        String durationString = Calculator.calculateDuration(duration.getSeconds(), lang);

        String viewCountString = "";
        String subscriberCountString = "";
        if (lang.equals("ko")) {
            viewCountString = Calculator.NumberToString("조회수 ", video.getViewCount(), "회", lang);
            subscriberCountString = Calculator.NumberToString("구독자 ", video.getSubscriberCount(), "명", lang);
        }
        else if (lang.equals("en")) {
            viewCountString = Calculator.NumberToString("", video.getViewCount(), " views", lang);
            subscriberCountString = Calculator.NumberToString("", video.getSubscriberCount(), " subscribers", lang);
        }

        VideoDto videoDto = VideoDto.builder()
            .id(video.getId())
            .channelId(video.getChannelId())
            .channelTitle(video.getChannelTitle())
            .description(video.getDescription())
            .title(video.getTitle())
            .publishedAt(durationString)
            .viewCount(viewCountString)
            .subscriberCount(subscriberCountString)
            .isExist(video.isExist())
            .build();
        return videoDto;
    }

    /**
     * 모든 동영상 리스트 조회
     *
     * @param lang 다국어 설정값
     * @return 모든 동영상 리스트
     */
    public List<VideoDto> getVideo(String lang) {
        List<VideoDto> videoDtoList = new ArrayList();
        List<Video> videoList = videoRepository.findAll();
        for (int i = 0; i < videoList.size(); i++) {
            videoDtoList.add(videoToVideoDto(videoList.get(i), lang));
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
     * @param lang 다국어 설정값
     * @return 랜덤으로 섞은 동영상 리스트
     */
    public List<VideoDto> getRandomVideo(String lang) {
        List<VideoDto> videoDtoList = new ArrayList();
        videoRepository.findAll()
            .forEach(video -> videoDtoList.add(videoToVideoDto(video, lang)));
        return combineVideoDtoList(videoDtoList);
    }

    /**
     * 동영상 삭제
     *
     * @param videoIds 삭제할 유튜브 비디오 아이디들이 담긴 map
     */
    public void deleteVideo(@RequestBody Map<String, Boolean> videoIds) {
        videoRepository.findAll()
            .forEach(video -> {
                if (videoIds.get(video.getId()) == null)
                    return;

                log.info("deleteVideo : {}", video);
                video.setExist(false);
                videoRepository.save(video);
            });
    }
}
