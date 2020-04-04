package com.zzd.api.service;

import com.zzd.api.domain.TRecommend;

/**
 * @author
 * @date
 * @describe
 */
public interface RecommendService {
    void addRecommend(TRecommend recommend);

    void removeRecommed(TRecommend recommend);

    boolean isInRecommend(TRecommend recommend);
}
