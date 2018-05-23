package net.h2.web.mob.file.profile.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.mob.file.profile.IProfileUploadAPI;
import net.h2.web.mob.file.profile.ProfileUploadEntity;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/file/profile")
public class ProfileUploadController {

	@Autowired
	Environment environment;	
	@Autowired
	IProfileUploadAPI profileUploadAPI;
	
	Logger logger = LoggerFactory.getLogger(ProfileUploadController.class);
	
	@ResponseBody
	@RequestMapping(value = "/upload",  method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request) throws IOException, BaseServerBusinessException {		

			Iterator<String> itr = request.getFileNames();
			String filePath = null;

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String originalFileName = file.getOriginalFilename();
				byte[] fileContent = file.getBytes();
				ServletContext servletContext = request.getSession().getServletContext();
				filePath = storeFile(servletContext,file.getInputStream(),originalFileName);
				ProfileUploadEntity profileUploadEntity = new ProfileUploadEntity();
				profileUploadEntity.setTitle(originalFileName);
				profileUploadEntity.setFileExtension(mimeType);
				profileUploadEntity.setFileSize(fileContent.length / 1024);
				profileUploadEntity.setCreatedDate(new Date());
				profileUploadEntity.setFilePath(filePath);		
				profileUploadAPI.save(profileUploadEntity);	
			}
			return filePath;
		
	}
	


	private String storeFile(ServletContext servletContext ,InputStream inputStream,String originalFileName) throws MalformedURLException {
		
		String urlStr = null;
		try {
			String filePath = servletContext.getRealPath(environment.getProperty("file.path"));
			String fielExtension = originalFileName.substring(originalFileName.lastIndexOf("."),originalFileName.length());
			String randomFileName = UUID.randomUUID().toString();
			String fileStoreName = filePath + "\\" +  randomFileName + fielExtension;			
			FileOutputStream outputStream = new FileOutputStream(fileStoreName);			
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
			outputStream.close();
			urlStr = environment.getProperty("file.path") + "\\" + randomFileName + fielExtension;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return urlStr;
	}





	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> errorHandler(Exception exc) {
		logger.error(exc.getMessage(), exc);
		if (exc instanceof BaseServerBusinessException) {
			String exceptionCode = ((BaseServerBusinessException) exc).getExceptionCode();
			String exceptionMsg = ((BaseServerBusinessException) exc).getExceptionCode();
			return new ResponseEntity<>(exceptionCode + ":" + exceptionMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
