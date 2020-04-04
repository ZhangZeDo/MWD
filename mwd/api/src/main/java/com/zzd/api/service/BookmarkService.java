package com.zzd.api.service;

import com.zzd.api.domain.TBookmark;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.BookmarkDTO;
import com.zzd.api.dto.PageResponseResult;

import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
public interface BookmarkService {
    PageResponseResult listBookmarkList(BookmarkDTO bookmarkDTO);

    void removeBookmark(TBookmark bookmark);

    void addBookmark(TBookmark bookmark);

    boolean isBookMark(TBookmark bookmark);
}
