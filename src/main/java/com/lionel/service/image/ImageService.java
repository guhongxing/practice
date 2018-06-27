package com.lionel.service.image;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	Map<String,Object> uploadPictures(List<MultipartFile> mfiles, HttpServletRequest request) throws IOException;

}
