package net.h2.web.core.config;

import java.util.Map;

import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer,ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		Map<String, CacheConfiguration> cacheConfigurationMap = applicationContext
				.getBeansOfType(CacheConfiguration.class);
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		for (CacheConfiguration cacheConfiguration : cacheConfigurationMap
				.values()) {
			config.addCache(cacheConfiguration);
		}

		return net.sf.ehcache.CacheManager.newInstance(config);

	}

  

	@Bean
	@Override
	public CacheManager cacheManager() {
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(
				ehCacheManager());
		return ehCacheCacheManager;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}