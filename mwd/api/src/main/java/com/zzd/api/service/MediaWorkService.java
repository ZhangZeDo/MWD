package com.zzd.api.service;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.MediaWorkDTO;
import com.zzd.api.dto.PageResponseResult;

/**
 * @author
 * @date
 * @describe
 */
public interface MediaWorkService {
    PageResponseResult mediaWorkList(MediaWorkDTO mediaWorkDTO,String operator);

    void addMediaWork(TMediaWork mediaWork, String operator);

    void approvalMediaWork(TMediaWork mediaWork,String operator);

    void changeMediaWorkStatus(TMediaWork mediaWork,String operator);

    TMediaWork selectMediaWorkById(String id);
}
