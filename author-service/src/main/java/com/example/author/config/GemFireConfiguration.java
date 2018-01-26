package com.example.author.config;

import java.util.Properties;

import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;

import com.example.author.model.Author;

@Configuration
@ClientCacheApplication
public class GemFireConfiguration {

	@Bean(name="author-information")
	LocalRegionFactoryBean<String, Author> getEmployee(final GemFireCache cache) {
		LocalRegionFactoryBean<String, Author> employeeRegion = new LocalRegionFactoryBean<String, Author>();
		employeeRegion.setCache(cache);
		employeeRegion.setName("author-information");
		return employeeRegion;
	}

	@Bean
	Properties gemfireProperties() {
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("name","SpringDataGemFireApplication");
		gemfireProperties.setProperty("mcast-port", "0");
		gemfireProperties.setProperty("log-level", "config");
		return gemfireProperties;
	}

	@Bean
	CacheFactoryBean gemfireCache() {
		CacheFactoryBean gemfireCache = new CacheFactoryBean();
		gemfireCache.setClose(true);
		gemfireCache.setProperties(gemfireProperties());
		return gemfireCache;
	}

}
