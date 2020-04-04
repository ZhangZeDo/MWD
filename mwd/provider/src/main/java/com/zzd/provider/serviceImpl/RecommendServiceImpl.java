package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TRecommendMapper;
import com.zzd.api.domain.TRecommend;
import com.zzd.api.domain.TRecommendExample;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.RecommendService;
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
public class RecommendServiceImpl implements RecommendService {
    private Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);

    @Resource
    private TRecommendMapper recommendMapper;

    @Override
    public void addRecommend(TRecommend recommend) {
        if (StringUtils.isBlank(recommend.getUserAccount()) || StringUtils.isBlank(recommend.getMediaId())){
            throw new BussException("非法入参");
        }
        recommend.setId(UniqIdUtil.getUniqId());
        resetRecommendInfo(recommend,recommend.getUserAccount());
        recommendMapper.insertSelective(recommend);
    }

    @Override
    public void removeRecommed(TRecommend recommend) {
        if (isInRecommend(recommend)){
            TRecommendExample example = new TRecommendExample();
            example.createCriteria().andUserAccountEqualTo(recommend.getUserAccount())
                    .andMediaIdEqualTo(recommend.getMediaId());
            recommendMapper.deleteByExample(example);
        }
    }

    @Override
    public boolean isInRecommend(TRecommend recommend) {
        if (StringUtils.isBlank(recommend.getUserAccount()) || StringUtils.isBlank(recommend.getMediaId())){
            throw new BussException("非法入参");
        }
        TRecommendExample example = new TRecommendExample();
        example.createCriteria().andUserAccountEqualTo(recommend.getUserAccount())
                                .andMediaIdEqualTo(recommend.getMediaId());
        List<TRecommend> recommendList = new ArrayList<>();
        recommendList = recommendMapper.selectByExample(example);
        if (recommendList.size() == 0){
            return false;
        }
        return true;
    }

    private void resetRecommendInfo(TRecommend recommend,String operator){
        if (StringUtils.isBlank(recommend.getCreateBy())){
            recommend.setCreateBy(operator);
            recommend.setCreateDatetime(new Date());
        }
        recommend.setUpdateBy(operator);
        recommend.setUpdateDatetime(new Date());
    }
}
