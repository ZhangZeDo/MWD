package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TUnderRankMapper;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TUnderRank;
import com.zzd.api.domain.TUnderRankExample;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.service.MediaWorkService;
import com.zzd.api.service.UnderRankService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class UnderRankServiceImpl implements UnderRankService {
    private Logger logger = LoggerFactory.getLogger(UnderRankServiceImpl.class);

    @Resource
    private TUnderRankMapper underRankMapper;
    @Resource
    private MediaWorkService mediaWorkService;

    @Override
    public void addUnderMedia(List<TMediaWork> mediaWorkList, String operator) {
        for (TMediaWork mediaWork : mediaWorkList) {
            TUnderRank underRank = new TUnderRank();
            underRank.setId(UniqIdUtil.getUniqId());
            underRank.setMediaId(mediaWork.getId());
            underRank.setMediaUser(mediaWork.getUploadUser());
            underRank.setStatus(EntityStatus.Valid.getCode());
            underRank.setSendResult(EntityStatus.InValid.getCode());
            resetUnderRankInfo(underRank,operator);
            underRankMapper.insertSelective(underRank);
            //todo 异步发送消息给创建人，并修改发送消息状态
        }
    }

    @Override
    public List<TMediaWork> queryAllUnderMedia(byte status) {
        List<TMediaWork> mediaWorkList = new ArrayList<>();
        List<TUnderRank> underRankList = new ArrayList<>();

        underRankList = queryAllUnderRank(status);
        for (TUnderRank underRank : underRankList) {
            TMediaWork mediaWork = new TMediaWork();
            mediaWork = mediaWorkService.selectMediaWorkById(underRank.getMediaId());
            if (mediaWork !=null){
                mediaWorkList.add(mediaWork);
            }
        }
        return mediaWorkList;
    }

    @Override
    public List<TUnderRank> queryAllUnderRank(byte status) {
        List<TUnderRank> underRankList = new ArrayList<>();
        TUnderRankExample example = new TUnderRankExample();
        example.createCriteria().andStatusEqualTo(status);
        underRankList = underRankMapper.selectByExample(example);
        return underRankList;
    }

    @Override
    public TUnderRank queryUnderRankById(String id) {
        TUnderRank underRank = new TUnderRank();
        underRank = underRankMapper.selectByPrimaryKey(id);
        return underRank;
    }

    @Override
    public void changeMediaStatus(TUnderRank tUnderRank, String operator) {
        byte status = tUnderRank.getStatus();
        tUnderRank = queryUnderRankById(tUnderRank.getId());
        resetUnderRankInfo(tUnderRank,operator);
        underRankMapper.updateByPrimaryKey(tUnderRank);
    }

    private void resetUnderRankInfo(TUnderRank underRank,String operator){
        if (StringUtils.isBlank(underRank.getCreateBy())){
            underRank.setCreateBy(operator);
            underRank.setCreateDatetime(new Date());
        }

        underRank.setUpdateBy(operator);
        underRank.setUpdateDatetime(new Date());

    }
}
