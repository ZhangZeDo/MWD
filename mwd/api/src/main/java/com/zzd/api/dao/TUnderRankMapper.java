package com.zzd.api.dao;

import com.zzd.api.domain.TUnderRank;
import com.zzd.api.domain.TUnderRankExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUnderRankMapper {
    int countByExample(TUnderRankExample example);

    int deleteByExample(TUnderRankExample example);

    int deleteByPrimaryKey(String id);

    int insert(TUnderRank record);

    int insertSelective(TUnderRank record);

    List<TUnderRank> selectByExample(TUnderRankExample example);

    TUnderRank selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TUnderRank record, @Param("example") TUnderRankExample example);

    int updateByExample(@Param("record") TUnderRank record, @Param("example") TUnderRankExample example);

    int updateByPrimaryKeySelective(TUnderRank record);

    int updateByPrimaryKey(TUnderRank record);
}
