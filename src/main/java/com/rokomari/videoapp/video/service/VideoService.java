package com.rokomari.videoapp.video.service;

import com.rokomari.videoapp.common.base.BaseRestTemplate;
import com.rokomari.videoapp.common.payload.ApiResponse;
import com.rokomari.videoapp.video.payload.VideoRequest;
import com.rokomari.videoapp.video.payload.Videos;
import com.rokomari.videoapp.video.payload.VideosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoService extends BaseRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    public ApiResponse crateVideo(Videos videos) throws Throwable {
        String url = getUrl()+"/video/add";
        return restTemplate.postForObject(url, videos, ApiResponse.class);
    }

    public VideosResponse videoSummary(VideoRequest videos) throws Throwable {
        String url = getUrl()+"/video/summary";
        return restTemplate.postForObject(url, videos, VideosResponse.class);
    }
}
