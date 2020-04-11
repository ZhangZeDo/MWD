package com.zzd.api.dao;

import java.util.List;

import com.zzd.api.domain.TMediaType;
import com.zzd.api.domain.TMediaTypeExample;
import com.zzd.api.dto.MediaTypeDTO;
import org.apache.ibatis.annotations.Param;

public interface TMediaTypeMapper {
    int countByExample(TMediaTypeExample example);

    int deleteByExample(TMediaTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TMediaType record);

    int insertSelective(TMediaType record);

    List<TMediaType> selectByExample(TMediaTypeExample example);

    TMediaType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TMediaType record, @Param("example") TMediaTypeExample example);

    int updateByExample(@Param("record") TMediaType record, @Param("example") TMediaTypeExample example);

    int updateByPrimaryKeySelective(TMediaType record);

    int updateByPrimaryKey(TMediaType record);

    List<TMediaType> selectMediaTypeListByDTO(@Param("param")MediaTypeDTO mediaTypeDTO);

    int selectMediaTypeTotalByDTO(@Param("param")MediaTypeDTO mediaTypeDTO);
}
