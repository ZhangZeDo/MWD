package com.zzd.api.dao;

import com.zzd.api.domain.TNews;
import com.zzd.api.domain.TNewsExample;
import com.zzd.api.dto.NewsDTO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TNewsMapper {
    int countByExample(TNewsExample example);

    int deleteByExample(TNewsExample example);

    int deleteByPrimaryKey(String id);

    int insert(TNews record);

    int insertSelective(TNews record);

    List<TNews> selectByExample(TNewsExample example);

    TNews selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByExample(@Param("record") TNews record, @Param("example") TNewsExample example);

    int updateByPrimaryKeySelective(TNews record);

    int updateByPrimaryKey(TNews record);

    List<TNews> selectByNewsDTO(@Param("param") NewsDTO newsDTO);

    int selectByNewsDTOTotal(@Param("param")NewsDTO newsDTO);
}
