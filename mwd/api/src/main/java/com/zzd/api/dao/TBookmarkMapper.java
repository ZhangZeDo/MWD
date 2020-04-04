package com.zzd.api.dao;

import java.util.List;

import com.zzd.api.domain.TBookmark;
import com.zzd.api.domain.TBookmarkExample;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.BookmarkDTO;
import org.apache.ibatis.annotations.Param;

public interface TBookmarkMapper {
    int countByExample(TBookmarkExample example);

    int deleteByExample(TBookmarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(TBookmark record);

    int insertSelective(TBookmark record);

    List<TBookmark> selectByExample(TBookmarkExample example);

    TBookmark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TBookmark record, @Param("example") TBookmarkExample example);

    int updateByExample(@Param("record") TBookmark record, @Param("example") TBookmarkExample example);

    int updateByPrimaryKeySelective(TBookmark record);

    int updateByPrimaryKey(TBookmark record);

    List<TMediaWork> selectMediaWorkByBookMark(@Param("param") BookmarkDTO bookmarkDTO);

    int selectMediaWorkByBookMarkTotal(@Param("param") BookmarkDTO bookmarkDTO);
}
