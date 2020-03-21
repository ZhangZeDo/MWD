package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TMediaWorkMapper;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.MediaWorkDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.MediaWorkService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class MediaWorkServiceImpl implements MediaWorkService {
    private Logger logger = LoggerFactory.getLogger(MediaWorkServiceImpl.class);

    @Resource
    private TMediaWorkMapper mediaWorkMapper;


    @Override
    public PageResponseResult mediaWorkList(MediaWorkDTO mediaWorkDTO, String operator) {
        Integer page = mediaWorkDTO.getPage();
        if (page == null || mediaWorkDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            mediaWorkDTO.setStartNum((page - 1) * mediaWorkDTO.getPageSize());
        } else {
            mediaWorkDTO.setStartNum(0);
        }
        if(StringUtils.isNotBlank(mediaWorkDTO.getSearchInput())){
            mediaWorkDTO.setSearchInput("%"+mediaWorkDTO.getSearchInput()+"%");
        }
        if(mediaWorkDTO.isBelongTo()){
            mediaWorkDTO.setUploadUser(operator);
        }

        List<TMediaWork> mediaWorkList = mediaWorkMapper.selectByMediaWorkDTO(mediaWorkDTO);
        int total = mediaWorkMapper.selectByMediaWorkTotal(mediaWorkDTO);
        return new PageResponseResult(total, mediaWorkList);
    }

    @Override
    public void addMediaWork(TMediaWork mediaWork,String operator) {
        try {
            mediaWork.setId(UniqIdUtil.getUniqId());
            mediaWork.setUploadUser(operator);
            mediaWork.setStatus(EntityStatus.InValid.getCode());
            setBaseInfoForWork(mediaWork);
            resetMediaWorkInfo(mediaWork,operator);
            mediaWorkMapper.insertSelective(mediaWork);
        }catch (Exception e){
            logger.error("文件上传记录保存失败，原因：",e);
            throw new BussException("上传失败");
        }
    }

    @Override
    public void approvalMediaWork(TMediaWork mediaWork,String operator) {
        try {
            byte status = mediaWork.getStatus();
            String approvalRemark = mediaWork.getApprovalRemark();
            mediaWork = mediaWorkMapper.selectByPrimaryKey(mediaWork.getId());
            mediaWork.setStatus(status);
            mediaWork.setApprovalUser(operator);
            mediaWork.setApprovalRemark(approvalRemark);
            resetMediaWorkInfo(mediaWork,operator);

            mediaWorkMapper.updateByPrimaryKeySelective(mediaWork);
        }catch (Exception e){
            logger.error("文件上传记录保存失败，原因：",e);
            throw new BussException("上传失败");
        }
    }

    private void resetMediaWorkInfo(TMediaWork mediaWork, String operator){
        if (StringUtils.isBlank(mediaWork.getCreateBy())){
            mediaWork.setCreateBy(operator);
            mediaWork.setCreateDatetime(new Date());
        }
        mediaWork.setUpdateBy(operator);
        mediaWork.setUpdateDatetime(new Date());
    }

    private void setBaseInfoForWork(TMediaWork mediaWork){
        mediaWork.setMediaGrade(Double.valueOf("0"));
        mediaWork.setDiscussNum(0);
        mediaWork.setPopularNum(0);
        mediaWork.setRecommendNum(0);
    }
}
