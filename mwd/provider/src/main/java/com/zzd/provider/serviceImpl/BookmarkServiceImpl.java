package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TBookmarkMapper;
import com.zzd.api.domain.TBookmark;
import com.zzd.api.domain.TBookmarkExample;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.BookmarkDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.BookmarkService;
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
public class BookmarkServiceImpl implements BookmarkService {
    private Logger logger = LoggerFactory.getLogger(BookmarkServiceImpl.class);

    @Resource
    private TBookmarkMapper bookmarkMapper;

    @Override
    public PageResponseResult listBookmarkList(BookmarkDTO bookmarkDTO) {
        Integer page = bookmarkDTO.getPage();
        if (page == null || bookmarkDTO.getPageSize() == null || StringUtils.isBlank(bookmarkDTO.getUserAccount())) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            bookmarkDTO.setStartNum((page - 1) * bookmarkDTO.getPageSize());
        } else {
            bookmarkDTO.setStartNum(0);
        }
        if(StringUtils.isNotBlank(bookmarkDTO.getSearchInput())){
            bookmarkDTO.setSearchInput("%"+bookmarkDTO.getSearchInput()+"%");
        }

        List<TMediaWork> mediaWorkList = new ArrayList<>();
        mediaWorkList = bookmarkMapper.selectMediaWorkByBookMark(bookmarkDTO);
        int total = bookmarkMapper.selectMediaWorkByBookMarkTotal(bookmarkDTO);
        return new PageResponseResult(total, mediaWorkList);
    }

    @Override
    public void removeBookmark(TBookmark bookmark) {
        if (isBookMark(bookmark)){
            TBookmarkExample example = new TBookmarkExample();
            example.createCriteria().andMediaIdEqualTo(bookmark.getMediaId())
                    .andUserAccountEqualTo(bookmark.getUserAccount());
            bookmarkMapper.deleteByExample(example);
        }
    }

    @Override
    public void addBookmark(TBookmark bookmark) {
        if (StringUtils.isBlank(bookmark.getMediaId()) || StringUtils.isBlank(bookmark.getUserAccount())){
            throw new BussException("非法入参");
        }
        bookmark.setId(UniqIdUtil.getUniqId());
        resetBookMarkInfo(bookmark,bookmark.getUserAccount());
        bookmarkMapper.insertSelective(bookmark);
    }

    @Override
    public boolean isBookMark(TBookmark bookmark) {
        if (StringUtils.isBlank(bookmark.getMediaId()) || StringUtils.isBlank(bookmark.getUserAccount())){
            throw new BussException("非法入参");
        }
        TBookmarkExample example = new TBookmarkExample();
        example.createCriteria().andMediaIdEqualTo(bookmark.getMediaId())
                .andUserAccountEqualTo(bookmark.getUserAccount());

        List<TBookmark> bookmarkList = new ArrayList<>();
        bookmarkList = bookmarkMapper.selectByExample(example);
        if (bookmarkList.size()==0){
            return false;
        }
        return true;
    }

    private void resetBookMarkInfo(TBookmark bookmark,String operator){
        if (StringUtils.isBlank(bookmark.getCreateBy())){
            bookmark.setCreateBy(operator);
            bookmark.setCreateDatetime(new Date());
        }
        bookmark.setUpdateBy(operator);
        bookmark.setUpdateDatetime(new Date());
    }
}
