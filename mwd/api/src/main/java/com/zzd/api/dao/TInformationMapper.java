package com.zzd.api.dao;

import java.util.List;

import com.zzd.api.domain.TInformation;
import com.zzd.api.domain.TInformationExample;
import org.apache.ibatis.annotations.Param;

public interface TInformationMapper {
    int countByExample(TInformationExample example);

    int deleteByExample(TInformationExample example);

    int deleteByPrimaryKey(String id);

    int insert(TInformation record);

    int insertSelective(TInformation record);

    List<TInformation> selectByExample(TInformationExample example);

    TInformation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TInformation record, @Param("example") TInformationExample example);

    int updateByExample(@Param("record") TInformation record, @Param("example") TInformationExample example);

    int updateByPrimaryKeySelective(TInformation record);

    int updateByPrimaryKey(TInformation record);
}
