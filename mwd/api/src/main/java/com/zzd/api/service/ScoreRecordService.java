package com.zzd.api.service;

import com.zzd.api.domain.TScoreRecord;

/**
 * @author
 * @date
 * @describe
 */
public interface ScoreRecordService {
    boolean isInScoreRecord(TScoreRecord scoreRecord,String operator);

    void addScoreRecord(TScoreRecord scoreRecord,String operator);

}
