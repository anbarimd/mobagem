package net.h2.web.core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
@PropertySources(@PropertySource("classpath:database.properties"))
public class HibernateConfig {

	
	@Autowired
	Environment environment;

	@Bean
	DataSource basicDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
		dataSource.setUsername(environment.getProperty("hibernate.connection.username"));
		dataSource.setPassword(environment.getProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean em = new LocalSessionFactoryBean();
		em.setDataSource(basicDataSource());
		em.setPackagesToScan("net.h2");
		em.setHibernateProperties(additionalProperties());
		try {
			em.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return em.getObject();
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(DataSource dataSource, SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setDataSource(dataSource);
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	@Bean
	public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		return transactionTemplate;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.validator.apply_to_ddl", "false");
		properties.put("hibernate.validator.autoregister_listeners", "false");
		properties.put("hibernate.dialect",environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.generate_statistics", "true");
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.use_sql_comments", "true");
		properties.put("hibernate.format_sql", "true");
		//properties.put("hibernate.cache.use_second_level_cache", "true");
		//properties.put("hibernate.cache.use_query_cache", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl"));		
		return properties;
	}
	
//	private Properties additionalProperties() {
//		Resource[] resources = new ClassPathResource[] { new ClassPathResource("database.properties") };
//		Properties pr = new Properties();
//		Properties hibernateConfg = new Properties();
//		try {
//			pr.load(new FileInputStream(resources[0].getFile()));
//			Set<Object> keys = pr.keySet();
//			for (Object object : keys) {
//				if (object instanceof String) {
//					if (((String) object).startsWith("hibernate") || ((String) object).startsWith("org.hibernate")) {
//						hibernateConfg.put((String) object, pr.getProperty((String) object));
//					}
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return hibernateConfg;
//	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor jdbcExceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
