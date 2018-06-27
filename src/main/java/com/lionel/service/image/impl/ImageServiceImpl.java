package com.lionel.service.image.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lionel.service.image.ImageService;
import com.lionel.utils.image.FileUploadUtil;
import com.lionel.utils.image.ImageProperties;


@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public Map<String, Object> uploadPictures(List<MultipartFile> mfiles, HttpServletRequest request) throws IOException {
		int i = 0;
   	 Map<String, Object> map = new HashMap<>();
   	 List<String> urlList=new ArrayList<String>();
        if (mfiles != null && mfiles.size() > 0) {
            // 文件上传部分
            for (MultipartFile file : mfiles) {
                i++;
                if (file != null && file.getSize() > 0) {
                    // 获取当前web服务器项目路径
                    String upload_path = request.getSession().getServletContext().getRealPath("/") + "fileupload/";
                    //获取文件名称
                    String fil_ename = file.getOriginalFilename();
                    // 获取文件后缀
                    String suffix = fil_ename.substring(fil_ename.lastIndexOf(".") + 1);
                    // File.separator
                    UUID uuid = UUID.randomUUID();
                    // 生成本地路径
                    File files = new File(upload_path + "");
                    if (!files.exists() && !files.isDirectory()) {
                        files.mkdir();
                    }
                    File localfile = new File(
                            upload_path + File.separator + uuid + "." + suffix);
                    // 添加文件
                    FileOutputStream foStream;
                    try {
                        foStream = FileUtils.openOutputStream(localfile);
                        foStream.write(file.getBytes());
                        foStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    File f = new File(localfile.getPath());
                    InputStream input = new FileInputStream(f);
                    byte[] content = new byte[input.available()];
                    input.read(content);
                    String result = ImageProperties.FILE_HOST+FileUploadUtil.uploadPicture(content, "/refundOrder/images/", FileUploadUtil.getSuffix(localfile.getPath()));
                    urlList.add(result);
                }
            }
            map.put("urls", urlList);
        }
		return map;
	}

}
