package com.zzd.consumer.controller;

import com.zzd.api.service.InformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@Controller
@RequestMapping("/information")
public class InformationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Resource
    private InformationService informationService;
}
