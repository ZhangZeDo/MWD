package com.zzd.api.service;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TUnderRank;

import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
public interface UnderRankService  {
    void addUnderMedia(List<TMediaWork> mediaWorkList,String operator);

    List<TMediaWork> queryAllUnderMedia(byte status);

    List<TUnderRank> queryAllUnderRank(byte status);

    TUnderRank queryUnderRankById(String id);

    void changeMediaStatus(TUnderRank tUnderRank,String operator);
}
