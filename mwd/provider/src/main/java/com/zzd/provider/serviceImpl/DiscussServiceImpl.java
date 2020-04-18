package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TDiscussMapper;
import com.zzd.api.domain.TDiscuss;
import com.zzd.api.domain.TDiscussExample;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.DiscussDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.DiscussService;
import com.zzd.api.service.MediaWorkService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class DiscussServiceImpl implements DiscussService {
    private static final Logger logger = LoggerFactory.getLogger(DiscussServiceImpl.class);

    @Resource
    private TDiscussMapper discussMapper;

    @Resource
    private MediaWorkService mediaWorkService;

    @Override
    public PageResponseResult selectDiscussList(DiscussDTO discussDTO) {
        Integer page = discussDTO.getPage();
        if (discussDTO.getPage() == null || discussDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            discussDTO.setStartNum((page - 1) * discussDTO.getPageSize());
        } else {
            discussDTO.setStartNum(0);
        }

        List<TDiscuss> discussList = discussMapper.selectByDiscussDTO(discussDTO);
        int total = discussMapper.selectByDiscussDTOTotal(discussDTO);
        return new PageResponseResult(total, discussList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sendDiscuss(TDiscuss discuss, String operator) {
        discuss.setId(UniqIdUtil.getUniqId());
        discuss.setDiscussUser(operator);
        discuss.setStatus(EntityStatus.Valid.getCode());
        resetDiscussInfo(discuss,operator);
        discussMapper.insertSelective(discuss);

        //同时在作品表视频评论+1
        TMediaWork mediaWork =  mediaWorkService.selectMediaWorkById(discuss.getMediaId());
        if (mediaWork.getDiscussNum() == null){
            mediaWork.setDiscussNum(0);
        }
        mediaWork.setDiscussNum(mediaWork.getDiscussNum()+1);
        mediaWorkService.updateMediaWork(mediaWork);
    }

    @Override
    public void changeDiscussStatus(TDiscuss discuss, String operator) {
        byte status = discuss.getStatus();
        discuss = selectDiscussById(discuss.getId());
        if (discuss == null){
            throw new BussException("要操作的内容已不存在");
        }
        discuss.setStatus(status);
        resetDiscussInfo(discuss,operator);
        discussMapper.updateByPrimaryKey(discuss);
    }

    @Override
    public TDiscuss selectDiscussById(String id) {
        TDiscuss discuss = new TDiscuss();
        discuss = discussMapper.selectByPrimaryKey(id);
        return discuss;
    }


    private void resetDiscussInfo(TDiscuss discuss,String operator){
        if (StringUtils.isBlank(discuss.getCreateBy())){
            discuss.setCreateBy(operator);
            discuss.setCreateDatetime(new Date());
        }
        discuss.setUpdateBy(operator);
        discuss.setUpdateDatetime(new Date());
    }
}
