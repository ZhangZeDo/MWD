package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TNewsMapper;
import com.zzd.api.domain.TDiscuss;
import com.zzd.api.domain.TNews;
import com.zzd.api.domain.TNewsExample;
import com.zzd.api.dto.NewsDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.NewsService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class NewsServiceImpl implements NewsService {
    private Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Resource
    private TNewsMapper newsMapper;

    @Override
    public PageResponseResult queryNewsList(NewsDTO newsDTO) {
        Integer page = newsDTO.getPage();
        if (newsDTO.getPage() == null || newsDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        if (StringUtils.isNotBlank(newsDTO.getSearchInput())){
            newsDTO.setSearchInput("%"+newsDTO.getSearchInput()+"%");
        }
        //构建limit start
        if (page > 0) {
            newsDTO.setStartNum((page - 1) * newsDTO.getPageSize());
        } else {
            newsDTO.setStartNum(0);
        }

        List<TNews> newsList = newsMapper.selectByNewsDTO(newsDTO);
        int total = newsMapper.selectByNewsDTOTotal(newsDTO);
        return new PageResponseResult(total, newsList);
    }

    @Override
    public void deleteNewsById(String newsId, String operator) {
        if (StringUtils.isBlank(newsId) || StringUtils.isBlank(operator)){
            throw new BussException("非法入参");
        }
        TNews news = selectNewsById(newsId);
        if (news == null){
            throw new BussException("要删除的新闻已不存在");
        }
        resetNewsInfo(news,operator);
        news.setStatus(EntityStatus.InValid.getCode());
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public TNews selectNewsById(String newsId) {
        TNewsExample example = new TNewsExample();
        example.createCriteria().andIdEqualTo(newsId).andStatusEqualTo(EntityStatus.Valid.getCode());
        List<TNews> newsList = newsMapper.selectByExample(example);
        if (newsList==null || newsList.size()==0){
            return null;
        }
        return newsList.get(0);
    }

    @Override
    public void addNews(TNews news, String operator) {
        news.setId(UniqIdUtil.getUniqId());
        news.setPublishUser(operator);
        news.setStatus(EntityStatus.Valid.getCode());
        resetNewsInfo(news,operator);
        newsMapper.insertSelective(news);
    }

    private void resetNewsInfo(TNews news,String operator){
        if (StringUtils.isBlank(news.getCreateBy())){
            news.setCreateBy(operator);
            news.setCreateDatetime(new Date());
        }
        news.setUpdateBy(operator);
        news.setUpdateDatetime(new Date());
    }


}
