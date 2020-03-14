package com.zzd.api.dao;

import java.util.List;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TMediaWorkExample;
import org.apache.ibatis.annotations.Param;


public interface TMediaWorkMapper {
    int countByExample(TMediaWorkExample example);

    int deleteByExample(TMediaWorkExample example);

    int deleteByPrimaryKey(String id);

    int insert(TMediaWork record);

    int insertSelective(TMediaWork record);

    List<TMediaWork> selectByExample(TMediaWorkExample example);

    TMediaWork selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TMediaWork record, @Param("example") TMediaWorkExample example);

    int updateByExample(@Param("record") TMediaWork record, @Param("example") TMediaWorkExample example);

    int updateByPrimaryKeySelective(TMediaWork record);

    int updateByPrimaryKey(TMediaWork record);
}
