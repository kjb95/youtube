package com.kjb95.backend.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public void addPlaylist(Map<String,Object> playlist) {
        String publishedAtStr = (String)playlist.get("publishedAt");
        publishedAtStr = publishedAtStr.substring(0, publishedAtStr.length()-1);
        LocalDateTime publishedAt = LocalDateTime.parse(publishedAtStr);

        String subscriberCountStr = (String)playlist.get("subscriberCount");
        Long subscriberCount = null;
        if (subscriberCountStr != null)
            subscriberCount = Long.parseLong(subscriberCountStr);

        Video video = Video.builder()
            .id((String)playlist.get("id"))
            .channelId((String)playlist.get("channelId"))
            .channelTitle((String)playlist.get("channelTitle"))
            .description((String)playlist.get("description"))
            .title((String)playlist.get("title"))
            .publishedAt(publishedAt)
            .viewCount(Long.parseLong((String)playlist.get("viewCount")))
            .subscriberCount(subscriberCount)
            .isExist(true)
            .build();

        videoRepository.save(video);
    }

    private VideoDto videoToVideoDto(Video video) {
        Duration duration = Duration.between(video.getPublishedAt(), LocalDateTime.now());
        String durationString = Calculator.calculateDuration(duration.getSeconds());
        String viewCountString = Calculator.NumberToString("조회수 ", video.getViewCount(), "회");
        String subscriberCountString = Calculator.NumberToString("구독자 " , video.getSubscriberCount(), "명");

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
    
    public List<VideoDto> getPlaylist() {
        List<VideoDto> videoDtoList = new ArrayList();
        List<Video> videoList = videoRepository.findAll();
        for(int i=0; i<videoList.size(); i++)
            videoDtoList.add(videoToVideoDto(videoList.get(i)));
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

    public List<VideoDto> getRandomPlaylist() {
        List<VideoDto> videoDtoList = new ArrayList();
        videoRepository.findAll().forEach(video -> videoDtoList.add(videoToVideoDto(video)));
        return combineVideoDtoList(videoDtoList);
    }

    public void deletePlaylist(@RequestBody Map<String,Object> playlistIds) {
        videoRepository.findAll().forEach(video -> {
            if (playlistIds.get(video.getId()) == null)
                return ;
            video.setExist(false);
            videoRepository.save(video);
        });
    }
}
