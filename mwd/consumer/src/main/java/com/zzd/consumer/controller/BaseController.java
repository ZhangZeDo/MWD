package com.zzd.consumer.controller;

import com.zzd.api.domain.TUser;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.LoginService;
import com.zzd.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 * @author
 * @date
 * @describe
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);


    public String getOperator(HttpServletRequest request){
        HttpSession session = request.getSession();
        TUser user = (TUser) session.getAttribute("userInfo");
        return user.getUserAccount();
    }

    public void downloadFile(String fileUrl,String fileName,HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            fileUrl = resetFileUrl(fileUrl);
            FileCopyUtils.copy(new FileInputStream(fileUrl), response.getOutputStream());
        } catch (Exception e) {
            logger.error("下载文件出现异常，异常信息为", e);
        }
    }

    private String resetFileUrl(String fileUrl){
        //http://localhost:8083/news/20200425/新建文本文档_2020-04-25-14-57-56.txt
        String baseUrl = "D:\\Program Files\\fileDepository\\";
        fileUrl = fileUrl.replace("http://localhost:8083/",baseUrl);
        fileUrl = fileUrl.replace("/","\\");
        return fileUrl;
    }
}
