package com.zzd.api.dao;

import com.zzd.api.domain.TJob;
import com.zzd.api.domain.TJobExample;
import com.zzd.api.dto.JobDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TJobMapper {
    int countByExample(TJobExample example);

    int deleteByExample(TJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(TJob record);

    int insertSelective(TJob record);

    List<TJob> selectByExample(TJobExample example);

    TJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TJob record, @Param("example") TJobExample example);

    int updateByExample(@Param("record") TJob record, @Param("example") TJobExample example);

    int updateByPrimaryKeySelective(TJob record);

    int updateByPrimaryKey(TJob record);

    List<TJob> selectByJobDTO(@Param("param") JobDTO jobDTO);

    int selectByJobDTOTotal(@Param("param")JobDTO jobDTO);
}
