package com.xdclass.online_xdclass.service;

import com.xdclass.online_xdclass.model.entity.Video;
import com.xdclass.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
