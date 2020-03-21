package com.zzd.api.utils;

import com.zzd.api.exceptions.BussException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @date
 * @describe
 */
public class FileUtil {
    private static final String coverBaseUrl = "D:\\Program Files\\fileDepository\\cover";
    private static final String mediaBaseUrl = "D:\\Program Files\\fileDepository\\media";

    public static String uploadFile(String type, InputStream inputStream,String fileName){
        try {
            String baseUrl = "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String date = simpleDateFormat.format(new Date());
            if (StringUtils.equals(type, "cover")) {
                baseUrl = coverBaseUrl+"\\"+date;
            } else if (StringUtils.equals(type, "media")) {
                baseUrl = mediaBaseUrl+"\\"+date;
            } else {
                throw new BussException("保存文件失败");
            }
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String dateString = "_".concat(simpleDateFormat.format(new Date()));
            String name = "";
            int i = fileName.lastIndexOf(".");
            if (i == -1) {
                name = fileName.concat(dateString);
            } else {
                name = fileName.substring(0, i).concat(dateString).concat(fileName.substring(i, fileName.length()));
            }
            String routeDir = baseUrl+"\\"+name;
            FileUtils.copyInputStreamToFile(inputStream, new File(routeDir));
            return routeDir;
        }catch (Exception e){
            throw new BussException("保存文件失败");
        }
    }


}
