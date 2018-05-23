package net.h2.web.core.config;

import java.util.Locale;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.h2.web.core.config.initializer.DozerIntializer;
import net.h2.web.mob.util.SmsUtil;

@Configuration
@ComponentScan(basePackages = "net.h2.web")
@EnableAsync
@Import({ MvcConfig.class, HibernateConfig.class, AOPConfig.class,CachingConfig.class,FileConfig.class,SecurityConfiguration.class })
public class CoreConfig {

	@Bean
	public ReloadableResourceBundleMessageSource resourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(new String[] { "/WEB-INF/locale/messages" });
		messageSource.setFallbackToSystemLocale(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		return localeChangeInterceptor;
	}

	@Bean
	public SmsUtil smsUtil() {
		SmsUtil smsUil = new SmsUtil();
		return smsUil;
	}

	@Bean
	public DozerBeanMapper  mapper(){
		DozerBeanMapper mapper = new DozerBeanMapper();
		//Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:dozer-bean-mappings.xml");
		//mapper.setMappingFiles(resources);
		//mapper.setCustomFieldMapper(dozerInitialize());
		return mapper;
	}
	
	@Bean
	public DozerIntializer dozerInitialize(){
		return new DozerIntializer();
	}

	public SessionLocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale("fa", "IR"));
		return sessionLocaleResolver;
	}	
	

}
