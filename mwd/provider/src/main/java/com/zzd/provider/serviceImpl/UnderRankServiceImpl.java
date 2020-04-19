package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TUnderRankMapper;
import com.zzd.api.domain.*;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.MediaWorkService;
import com.zzd.api.service.UnderRankService;
import com.zzd.api.service.UserService;
import com.zzd.provider.utils.MailSendUtils;
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
public class UnderRankServiceImpl implements UnderRankService {
    private Logger logger = LoggerFactory.getLogger(UnderRankServiceImpl.class);

    @Resource
    private TUnderRankMapper underRankMapper;
    @Resource
    private MediaWorkService mediaWorkService;
    @Resource
    private MailSendUtils mailSendUtils;
    @Resource
    private UserService userService;

    @Override
    public void addUnderMedia(List<TMediaWork> mediaWorkList, String operator) {
        for (TMediaWork mediaWork : mediaWorkList) {
            TUnderRank underRank = new TUnderRank();
            underRank.setId(UniqIdUtil.getUniqId());
            underRank.setMediaId(mediaWork.getId());
            underRank.setMediaUser(mediaWork.getUploadUser());
            underRank.setStatus(EntityStatus.Valid.getCode());
            underRank.setSendResult(EntityStatus.InValid.getCode());
            resetUnderRankInfo(underRank,operator);
            underRankMapper.insertSelective(underRank);
            try {
                sendEmailToOwner(mediaWork);
                logger.info("邮件通知发送成功，修改消息状态为成功");
                underRank.setSendResult(EntityStatus.Valid.getCode());
                underRankMapper.updateByPrimaryKey(underRank);
            }catch (Exception e){
                logger.info("邮件通知发送失败,原因：{}",e);
            }
        }
    }

    private void sendEmailToOwner(TMediaWork mediaWork){
        String owner = mediaWork.getUploadUser();
        TUser user = userService.selectUserByAccount(owner,EntityStatus.All.getCode());
        if (user == null || user.getUserMail()==null){
            logger.error("邮件通知是，收件人信息或邮箱信息为空！");
            throw new BussException("邮件通知是，收件人信息或邮箱信息为空！");
        }
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("系统通知");
        emailModel.setRecieverName(user.getUserName());
        String content = String.format("%s您好，恭喜您，你的作品%s被选中为优秀作品之一，快登陆系统查看吧！",user.getUserName(),mediaWork.getMediaName());
        emailModel.setEmailContent(content);
        emailModel.setRecieverEmailAddress(user.getUserMail());
        mailSendUtils.sendEmailAsText(emailModel);
    }

    @Override
    public List<TMediaWork> queryAllUnderMedia(byte status) {
        List<TMediaWork> mediaWorkList = new ArrayList<>();
        List<TUnderRank> underRankList = new ArrayList<>();

        underRankList = queryAllUnderRank(status);
        for (TUnderRank underRank : underRankList) {
            TMediaWork mediaWork = new TMediaWork();
            mediaWork = mediaWorkService.selectMediaWorkById(underRank.getMediaId());
            if (mediaWork !=null){
                mediaWorkList.add(mediaWork);
            }
        }
        return mediaWorkList;
    }

    @Override
    public List<TUnderRank> queryAllUnderRank(byte status) {
        List<TUnderRank> underRankList = new ArrayList<>();
        TUnderRankExample example = new TUnderRankExample();
        example.createCriteria().andStatusEqualTo(status);
        underRankList = underRankMapper.selectByExample(example);
        return underRankList;
    }

    @Override
    public TUnderRank queryUnderRankById(String id) {
        TUnderRank underRank = new TUnderRank();
        underRank = underRankMapper.selectByPrimaryKey(id);
        return underRank;
    }

    @Override
    public void changeMediaStatus(TUnderRank tUnderRank, String operator) {
        byte status = tUnderRank.getStatus();
        tUnderRank = queryUnderRankById(tUnderRank.getId());
        resetUnderRankInfo(tUnderRank,operator);
        underRankMapper.updateByPrimaryKey(tUnderRank);
    }

    private void resetUnderRankInfo(TUnderRank underRank,String operator){
        if (StringUtils.isBlank(underRank.getCreateBy())){
            underRank.setCreateBy(operator);
            underRank.setCreateDatetime(new Date());
        }

        underRank.setUpdateBy(operator);
        underRank.setUpdateDatetime(new Date());

    }
}
