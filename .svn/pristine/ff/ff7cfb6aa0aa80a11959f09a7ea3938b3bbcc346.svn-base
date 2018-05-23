package net.h2.web.core.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;

//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
public class JPAConfig {

	@Autowired
	Environment environment;

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
		dataSource.setUsername(environment
				.getProperty("hibernate.connection.username"));
		dataSource.setPassword(environment
				.getProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager());
		return transactionTemplate;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("JPADB");
		em.setPackagesToScan("net.ictcert.springtest");
		em.setJpaVendorAdapter(jpaVendorAdaper());
		em.setJpaPropertyMap(additionalProperties());
		em.afterPropertiesSet();
		return em;
	}
	
	

	@Bean
	public JpaVendorAdapter jpaVendorAdaper() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		return vendorAdapter;
	}

	private Map<String,?> additionalProperties() {
		Map<String,String> jpaPropertiesMap = new HashMap<String,String>();
		jpaPropertiesMap.put("hibernate.validator.apply_to_ddl", "false");
		jpaPropertiesMap.put("hibernate.validator.autoregister_listeners", "false");
		jpaPropertiesMap.put("hibernate.dialect",environment.getProperty("hibernate.dialect"));
		jpaPropertiesMap.put("hibernate.generate_statistics", "true");
		jpaPropertiesMap.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		jpaPropertiesMap.put("hibernate.use_sql_comments", "true");
		jpaPropertiesMap.put("hibernate.format_sql", "true");
		jpaPropertiesMap.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl"));
		return jpaPropertiesMap;
	}

}
