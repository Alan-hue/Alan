package com.xdclass.online_xdclass.service;

import com.xdclass.online_xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    int save(int useId, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
