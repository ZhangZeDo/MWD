package com.zzd.api.dao;

import java.util.List;

import com.zzd.api.domain.TNews;
import com.zzd.api.domain.TNewsExample;
import com.zzd.api.dto.NewsDTO;
import org.apache.ibatis.annotations.Param;

public interface TNewsMapper {
    int countByExample(TNewsExample example);

    int deleteByExample(TNewsExample example);

    int deleteByPrimaryKey(String id);

    int insert(TNews record);

    int insertSelective(TNews record);

    List<TNews> selectByExampleWithBLOBs(TNewsExample example);

    List<TNews> selectByExample(TNewsExample example);

    TNews selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByExample(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByPrimaryKeySelective(TNews record);

    int updateByPrimaryKeyWithBLOBs(TNews record);

    int updateByPrimaryKey(TNews record);

    List<TNews> selectByNewsDTO(@Param("param")NewsDTO newsDTO);

    int selectByNewsDTOTotal(@Param("param")NewsDTO newsDTO);
}
