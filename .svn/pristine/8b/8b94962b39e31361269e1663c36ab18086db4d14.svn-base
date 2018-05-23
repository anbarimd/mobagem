package net.h2.web.core.config;

import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MobagemCacheConfig {
	
	@Bean
	public CacheConfiguration systemKeyCache() {
		CacheConfiguration profileCacheConfig = new CacheConfiguration();
		profileCacheConfig.setName("systemKeyCache");
		profileCacheConfig.setMaxEntriesLocalHeap(500);
		profileCacheConfig.setTimeToLiveSeconds(43200);
		return profileCacheConfig;
	}
	
	@Bean
	public CacheConfiguration mainPageFileCache() {
		CacheConfiguration profileCacheConfig = new CacheConfiguration();
		profileCacheConfig.setName("mainPageFileCache");
		profileCacheConfig.setMaxEntriesLocalHeap(100);
		profileCacheConfig.setTimeToLiveSeconds(600);
		return profileCacheConfig;
	}

	

}
