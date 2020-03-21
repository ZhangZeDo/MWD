package com.zzd.api.service;

import com.zzd.api.domain.TMediaType;

import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
public interface MediaTypeService {
    List<TMediaType> getAllMediaType(byte status);

    void addMediaType(TMediaType mediaType,String operator);

    void changeMediaTypeStatus(TMediaType mediaType,String operator);

    TMediaType queryMediaTypeById(String mediaTypeId);
}
