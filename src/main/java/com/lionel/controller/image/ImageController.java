package com.lionel.controller.image;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lionel.base.base.ResponseBean;
import com.lionel.service.image.ImageService;



@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	/*@Autowired
	public ImageController(ImageService imageService) {
		this.imageService=imageService;
	}*/
	
	//上传图片
    @RequestMapping("/uploadPictures")
    public ResponseBean uploadPictures(List<MultipartFile> mfiles, HttpServletRequest request) throws IOException {
    	 
    	Map<String,Object>	map=imageService.uploadPictures(mfiles, request);
    	
    	return ResponseBean.success(map);
    }

}
