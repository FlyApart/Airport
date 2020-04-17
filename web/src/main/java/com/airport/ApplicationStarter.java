package com.airport;

import com.airport.config.core.AdditionalPropertiesConfig;
import com.airport.config.core.DatabaseConfig;
import com.airport.config.core.JdbcTemplateConfig;
import com.airport.config.swagger.SwaggerConfig;
import com.airport.security.config.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.airport"}, exclude = {JacksonAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableCaching
@Import({
		DatabaseConfig.class,
		JdbcTemplateConfig.class,
		SwaggerConfig.class,
		AdditionalPropertiesConfig.class,
		SecurityConstants.class
})
public class ApplicationStarter extends SpringBootServletInitializer {

	public static void main (String[] args) {
		SpringApplication.run (ApplicationStarter.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder ();
	}

	@Autowired
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource, AdditionalPropertiesConfig jpaProperties) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();

		em.setDataSource (dataSource);
		em.setPackagesToScan ("com.airport");
		em.setJpaProperties (jpaProperties.getAdditionalProperties ());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
		em.setJpaVendorAdapter (vendorAdapter);
		return em;
	}

	@Bean(name = "entityManager")
	public EntityManager getEntityManager (EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager ();
	}

	/*@Bean
	public CacheManager cacheManager () {
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager ("passengerInfo");
		caffeineCacheManager.setCaffeine (cacheRoles ());
		return caffeineCacheManager;
	}

	public Caffeine<Object, Object> cacheRoles () {
		return Caffeine.newBuilder ()
		               .expireAfterAccess (10, TimeUnit.MINUTES)
		               .weakKeys ()
		               .initialCapacity (100)
		               .maximumSize (500)
		               .recordStats ();
	}*/

	//TODO
	// add safe delete
	// email verification
	// how to working btree


}


