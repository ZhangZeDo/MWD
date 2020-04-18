package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TScoreRecordMapper;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TScoreRecord;
import com.zzd.api.domain.TScoreRecordExample;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.MediaWorkService;
import com.zzd.api.service.ScoreRecordService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class ScoreRecordImpl implements ScoreRecordService {
    private Logger logger = LoggerFactory.getLogger(ScoreRecordImpl.class);

    @Resource
    private TScoreRecordMapper scoreRecordMapper;

    @Resource
    private MediaWorkService mediaWorkService;

    @Override
    public boolean isInScoreRecord(TScoreRecord scoreRecord, String operator) {
        TScoreRecordExample example = new TScoreRecordExample();
        example.createCriteria().andUserAccountEqualTo(operator).andMediaIdEqualTo(scoreRecord.getMediaId());
        List<TScoreRecord> scoreRecordList = new ArrayList<>();
        scoreRecordList = scoreRecordMapper.selectByExample(example);
        if (scoreRecordList.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public void addScoreRecord(TScoreRecord scoreRecord, String operator) {
        if(scoreRecord.getScore()==null || scoreRecord.getMediaId()==null){
            throw new BussException("非法入参");
        }
        String mediaId = scoreRecord.getMediaId();
        double score = scoreRecord.getScore();

        resetScoreRecordInfo(scoreRecord,operator);
        scoreRecord.setId(UniqIdUtil.getUniqId());
        scoreRecord.setUserAccount(operator);
        scoreRecordMapper.insertSelective(scoreRecord);

        //如果评分不为0，则同步到作品表
        if (score!=0) {
            TMediaWork mediaWork = mediaWorkService.selectMediaWorkById(mediaId);
            if (mediaWork.getMediaGrade() != null && mediaWork.getMediaGrade() != 0) {
                score = (mediaWork.getMediaGrade() + score) / 2;
            }
            mediaWork.setMediaGrade(score);
            mediaWorkService.updateMediaWork(mediaWork);
        }
    }

    private void resetScoreRecordInfo(TScoreRecord record,String operator){
        if (StringUtils.isBlank(record.getCreateBy())){
            record.setCreateBy(operator);
            record.setCreateDatetime(new Date());
        }
        record.setUpdateBy(operator);
        record.setUpdateDatetime(new Date());
    }
}
