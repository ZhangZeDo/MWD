package com.zzd.api.service;

import com.zzd.api.domain.TDiscuss;
import com.zzd.api.dto.DiscussDTO;
import com.zzd.api.dto.PageResponseResult;

/**
 * @author
 * @date
 * @describe
 */
public interface DiscussService {
    PageResponseResult selectDiscussList(DiscussDTO discussDTO);

    void sendDiscuss(TDiscuss discuss,String operator);

    void changeDiscussStatus(TDiscuss discuss,String operator);

    TDiscuss selectDiscussById(String id);
}
