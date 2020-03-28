package com.zzd.api.dao;

import com.zzd.api.domain.TScoreRecord;
import com.zzd.api.domain.TScoreRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TScoreRecordMapper {
    int countByExample(TScoreRecordExample example);

    int deleteByExample(TScoreRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TScoreRecord record);

    int insertSelective(TScoreRecord record);

    List<TScoreRecord> selectByExample(TScoreRecordExample example);

    TScoreRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TScoreRecord record, @Param("example") TScoreRecordExample example);

    int updateByExample(@Param("record") TScoreRecord record, @Param("example") TScoreRecordExample example);

    int updateByPrimaryKeySelective(TScoreRecord record);

    int updateByPrimaryKey(TScoreRecord record);
}
