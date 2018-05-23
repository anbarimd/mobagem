package net.h2.web.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySources(value = {@PropertySource("classpath:file.properties")})
public class FileConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver result = new CommonsMultipartResolver();
		String maxSize = environment.getProperty("file.upload.max.size");
		if(maxSize.toLowerCase().endsWith("k"))
			result.setMaxUploadSize(Long.valueOf(maxSize.substring(0,maxSize.length() - 1)) *  1024);
		else
			result.setMaxUploadSize(200 * 1024);//200K		
		return result;
	}

}
