package com.zzd.api.dao;

import com.zzd.api.domain.TRecommend;
import com.zzd.api.domain.TRecommendExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRecommendMapper {
    int countByExample(TRecommendExample example);

    int deleteByExample(TRecommendExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRecommend record);

    int insertSelective(TRecommend record);

    List<TRecommend> selectByExample(TRecommendExample example);

    TRecommend selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRecommend record, @Param("example") TRecommendExample example);

    int updateByExample(@Param("record") TRecommend record, @Param("example") TRecommendExample example);

    int updateByPrimaryKeySelective(TRecommend record);

    int updateByPrimaryKey(TRecommend record);
}
