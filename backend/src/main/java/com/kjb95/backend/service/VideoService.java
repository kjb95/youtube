package com.kjb95.backend.service;

import com.kjb95.backend.dto.AddPlaylistDto;
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

    public void addPlaylist(AddPlaylistDto addPlaylistDto) {
        Video video = addPlaylistDtoToVideo(addPlaylistDto);
        log.info("addPlaylist : {}", video.toString());
        videoRepository.save(video);
    }

    private Video addPlaylistDtoToVideo(AddPlaylistDto addPlaylistDto) {
        String publishedAtStr = addPlaylistDto.getPublishedAt();
        publishedAtStr = publishedAtStr.substring(0, publishedAtStr.length()-1);
        LocalDateTime publishedAt = LocalDateTime.parse(publishedAtStr);

        Video video = Video.builder()
            .id(addPlaylistDto.getId())
            .channelId(addPlaylistDto.getChannelId())
            .channelTitle(addPlaylistDto.getChannelTitle())
            .description(addPlaylistDto.getDescription())
            .title(addPlaylistDto.getTitle())
            .publishedAt(publishedAt)
            .viewCount(addPlaylistDto.getViewCount())
            .isExist(true)
            .build();
        if (addPlaylistDto.getSubscriberCount() == null)
            video.setSubscriberCount(-1);
        else
            video.setSubscriberCount(addPlaylistDto.getSubscriberCount());

        return video;
    }

    private VideoDto videoToVideoDto(Video video, String lang) {
        Duration duration = Duration.between(video.getPublishedAt(), LocalDateTime.now());
        String durationString = Calculator.calculateDuration(duration.getSeconds(), lang);

        String viewCountString = "";
        String subscriberCountString = "";
        if (lang.equals("ko")) {
            viewCountString = Calculator.NumberToString("조회수 ", video.getViewCount(), "회", lang);
            subscriberCountString = Calculator.NumberToString("구독자 " , video.getSubscriberCount(), "명", lang);
        }
        else if (lang.equals("en")) {
            viewCountString = Calculator.NumberToString("", video.getViewCount(), " views", lang);
            subscriberCountString = Calculator.NumberToString("" , video.getSubscriberCount(), " subscribers", lang);
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
    
    public List<VideoDto> getPlaylist(String lang) {
        List<VideoDto> videoDtoList = new ArrayList();
        List<Video> videoList = videoRepository.findAll();
        for(int i=0; i<videoList.size(); i++)
            videoDtoList.add(videoToVideoDto(videoList.get(i), lang));
        return videoDtoList;
    }

    private List<VideoDto> combineVideoDtoList(List<VideoDto> videoDtoList) {
        for(int i=0; i<videoDtoList.size(); i++) {
            VideoDto temp = videoDtoList.get(i);
            int randomNumber = (int)(Math.random() * videoDtoList.size());
            videoDtoList.set(i, videoDtoList.get(randomNumber));
            videoDtoList.set(randomNumber, temp);
        }
        return videoDtoList;
    }

    public List<VideoDto> getRandomPlaylist(String lang) {
        List<VideoDto> videoDtoList = new ArrayList();
        videoRepository.findAll().forEach(video -> videoDtoList.add(videoToVideoDto(video, lang)));
        return combineVideoDtoList(videoDtoList);
    }

    public void deletePlaylist(@RequestBody Map<String,Boolean> playlistIds) {
        videoRepository.findAll().forEach(video -> {
            if (playlistIds.get(video.getId()) == null)
                return ;

            log.info("deletePlaylist : {}", video.toString());
            video.setExist(false);
            videoRepository.save(video);
        });
    }
}
