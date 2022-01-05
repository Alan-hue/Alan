package com.xdclass.online_xdclass.service.impl;

import com.xdclass.online_xdclass.exception.XDException;
import com.xdclass.online_xdclass.mapper.EpisodeMapper;
import com.xdclass.online_xdclass.mapper.PlayRecordMapper;
import com.xdclass.online_xdclass.mapper.VideoMapper;
import com.xdclass.online_xdclass.mapper.VideoOrderMapper;
import com.xdclass.online_xdclass.model.entity.Episode;
import com.xdclass.online_xdclass.model.entity.PlayRecord;
import com.xdclass.online_xdclass.model.entity.Video;
import com.xdclass.online_xdclass.model.entity.VideoOrder;
import com.xdclass.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;
    /**
     * 下单操作
     *
     * @param useId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int useId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(useId,videoId,1);

        if (videoOrder != null){
            return 0;
        }

        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(useId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        //生成播放记录
        if (rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);

            if (episode==null){
                throw new XDException(-1,"视频没有，请运营人员检测");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setVideoId(videoId);
            playRecord.setUserId(useId);
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setEpisodeId(episode.getId());
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
