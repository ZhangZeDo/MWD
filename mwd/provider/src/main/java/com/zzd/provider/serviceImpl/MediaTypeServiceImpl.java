package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TMediaTypeMapper;
import com.zzd.api.domain.TMediaType;
import com.zzd.api.domain.TMediaTypeExample;
import com.zzd.api.dto.MediaTypeDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.MediaTypeService;
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
public class MediaTypeServiceImpl implements MediaTypeService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private TMediaTypeMapper mediaTypeMapper;

    @Override
    public List<TMediaType> getAllMediaType(byte status) {
        try {
            List<TMediaType> mediaTypeList = new ArrayList<>();
            TMediaTypeExample example = new TMediaTypeExample();
            if (status == EntityStatus.All.getCode()){
                example.createCriteria();
            }else {
                example.createCriteria().andStatusEqualTo(status);
            }
            mediaTypeList = mediaTypeMapper.selectByExample(example);
            return mediaTypeList;
        }catch (Exception e){
            logger.error("获取所有作品类型失败，原因：",e);
            throw new BussException("获取所有作品类型失败");
        }
    }

    @Override
    public void addMediaType(TMediaType mediaType,String operator) {
        try {
            mediaType.setId(UniqIdUtil.getUniqId());
            mediaType.setStatus(EntityStatus.Valid.getCode());
            resetMediaTypeInfo(mediaType,operator);

            mediaTypeMapper.insertSelective(mediaType);
        }catch (Exception e){
            logger.error("新增作品类型失败，原因：",e);
            throw new BussException("新增作品类型失败");
        }
    }

    @Override
    public void changeMediaTypeStatus(TMediaType mediaType,String operator) {
        try {
            byte status= mediaType.getStatus();
            mediaType = mediaTypeMapper.selectByPrimaryKey(mediaType.getId());
            if (status == EntityStatus.InValid.getCode()){
                mediaType.setStatus(EntityStatus.Valid.getCode());
            }else if (status == EntityStatus.Valid.getCode()){
                mediaType.setStatus(EntityStatus.InValid.getCode());
            }else{
                throw new BussException("作品类型状态异常");
            }
            resetMediaTypeInfo(mediaType,operator);
            mediaTypeMapper.updateByPrimaryKeySelective(mediaType);
        }catch (Exception e){
            logger.error("修改作品类型状态失败，原因：",e);
            throw new BussException("修改作品类型状态失败");
        }
    }

    @Override
    public TMediaType queryMediaTypeById(String mediaTypeId) {
        try {
            TMediaType mediaType = new TMediaType();
            mediaType = mediaTypeMapper.selectByPrimaryKey(mediaTypeId);
            return mediaType;
        }catch (Exception e){
            logger.error("查询作品类型失败，原因：",e);
            throw new BussException("查询作品类型失败");
        }
    }

    @Override
    public PageResponseResult queryMediaTypeListByDTO(MediaTypeDTO mediaTypeDTO) {
        Integer page = mediaTypeDTO.getPage();
        if (page == null || mediaTypeDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            mediaTypeDTO.setStartNum((page - 1) * mediaTypeDTO.getPageSize());
        } else {
            mediaTypeDTO.setStartNum(0);
        }
        if(StringUtils.isNotBlank(mediaTypeDTO.getSearchInput())){
            mediaTypeDTO.setSearchInput("%"+mediaTypeDTO.getSearchInput()+"%");
        }

        List<TMediaType> mediaTypeList = mediaTypeMapper.selectMediaTypeListByDTO(mediaTypeDTO);
        int total = mediaTypeMapper.selectMediaTypeTotalByDTO(mediaTypeDTO);

        return new PageResponseResult(total,mediaTypeList);
    }

    private void resetMediaTypeInfo(TMediaType mediaType,String operator){
        if (StringUtils.isBlank(mediaType.getCreateBy())){
            mediaType.setCreateBy(operator);
            mediaType.setCreateDatetime(new Date());
        }
        mediaType.setUpdateBy(operator);
        mediaType.setUpdateDatetime(new Date());
    }
}
