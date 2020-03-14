package com.zzd.api.dao;


import java.util.List;

import com.zzd.api.domain.TDiscuss;
import com.zzd.api.domain.TDiscussExample;
import org.apache.ibatis.annotations.Param;

public interface TDiscussMapper {
    int countByExample(TDiscussExample example);

    int deleteByExample(TDiscussExample example);

    int deleteByPrimaryKey(String id);

    int insert(TDiscuss record);

    int insertSelective(TDiscuss record);

    List<TDiscuss> selectByExample(TDiscussExample example);

    TDiscuss selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TDiscuss record, @Param("example") TDiscussExample example);

    int updateByExample(@Param("record") TDiscuss record, @Param("example") TDiscussExample example);

    int updateByPrimaryKeySelective(TDiscuss record);

    int updateByPrimaryKey(TDiscuss record);
}
