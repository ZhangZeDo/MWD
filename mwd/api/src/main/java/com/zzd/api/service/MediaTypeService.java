package com.zzd.api.service;

import com.zzd.api.domain.TMediaType;
import com.zzd.api.dto.MediaTypeDTO;
import com.zzd.api.dto.PageResponseResult;

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

    PageResponseResult queryMediaTypeListByDTO(MediaTypeDTO mediaTypeDTO);
}
