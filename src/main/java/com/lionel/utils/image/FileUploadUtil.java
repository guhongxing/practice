package com.lionel.utils.image;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileUploadUtil {

    private static String UPLOAD_HOST;

    static {
        UPLOAD_HOST = ImageProperties.FILE_UPLOAD_HOST;
    }

    public static String uploadPicture(byte[] content, String path, String ext) {
        Date date = new Date();
        return uploadPicture(content, path, date.getTime() + "", ext);
    }

    public static String uploadPicture(byte[] content, String path, String fileName, String ext) {
        if (content == null || StringUtils.isEmpty(path) || StringUtils.isEmpty(fileName) || StringUtils.isEmpty(ext))
            return null;
        try {
            boolean result = upload(content, path, fileName, ext);
            if (result) {
                return path + fileName + "." + ext;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean upload(byte[] content, String path, String fileName, String ext) {
        try {
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("fileName", fileName + "." + ext);
            paramsMap.put("udfd", path);
            paramsMap.put("verificationCode", "123");
            paramsMap.put("newVerificationCode", "123");
            String result = HttpUtils.postFile(UPLOAD_HOST, content, paramsMap, ext);
            if (result.contains("69001")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getSuffix(String url) {
        url = url.toLowerCase();
        if (url.lastIndexOf(".") > 0)
            return url.substring(url.lastIndexOf(".") + 1);
        return null;
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/jhsrk/Desktop/1.jpg";
        File f = new File(path);
        InputStream input = new FileInputStream(f);
        byte[] content = new byte[input.available()];
        input.read(content);
        String result = uploadPicture(content, "/order/", getSuffix(path));
        System.out.println(result);
    }

}
