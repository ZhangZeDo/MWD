package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TInformationMapper;
import com.zzd.api.service.InformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class InformationServiceImpl implements InformationService {
    private Logger logger = LoggerFactory.getLogger(InformationServiceImpl.class);

    @Resource
    private TInformationMapper informationMapper;
}
