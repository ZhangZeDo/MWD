package com.zzd.api.utils;

import com.zzd.api.exceptions.BussException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
    private static final String newsBaseUrl = "D:\\Program Files\\fileDepository\\news";

    public static String uploadFile(String type, InputStream inputStream,String fileName){
        try {
            String baseUrl = "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String date = simpleDateFormat.format(new Date());
            String result = "http://129.204.251.179:8083/";
            if (StringUtils.equals(type, "cover")) {
                baseUrl = coverBaseUrl+"\\"+date;
                result = result + "cover/"+date;
            } else if (StringUtils.equals(type, "media")) {
                baseUrl = mediaBaseUrl+"\\"+date;
                result = result + "media/"+date;
            } else if (StringUtils.equals(type,"news")){
                baseUrl = newsBaseUrl+"\\"+date;
                result = result + "news/"+date;
            }else {
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

            result = result + "/" + name;
            return result;
        }catch (Exception e){
            throw new BussException("保存文件失败");
        }
    }


}
