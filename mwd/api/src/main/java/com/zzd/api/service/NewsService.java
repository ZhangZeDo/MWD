package com.zzd.api.service;

import com.zzd.api.domain.TNews;
import com.zzd.api.dto.NewsDTO;
import com.zzd.api.dto.PageResponseResult;

/**
 * @author
 * @date
 * @describe
 */
public interface NewsService {
    PageResponseResult queryNewsList(NewsDTO newsDTO);

    void deleteNewsById(String newsId,String operator);

    TNews selectNewsById(String newsId);
}
