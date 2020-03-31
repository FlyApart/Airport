package com.airline;

import com.airline.config.core.DatabaseConfig;
import com.airline.config.core.JdbcTemplateConfig;
import com.airline.config.core.AdditionalPropertiesConfig;
import com.airline.config.swagger.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
@SpringBootApplication(scanBasePackages = {"com.airline"}, exclude = {JacksonAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@Import({DatabaseConfig.class, JdbcTemplateConfig.class, SwaggerConfig.class, AdditionalPropertiesConfig.class})

//@EntityScan(basePackages = { "com.airline.entity" })
public class ApplicationStarter extends SpringBootServletInitializer {

	public static void main (String[] args) {
		SpringApplication.run (ApplicationStarter.class, args);
	}

/*	private Properties getAdditionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "validate");
		properties.put("hibernate.archive.autodetection", "class, hbm");
		properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		return properties;
	}*/

  /*  @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        // Package contain entity classes
        factoryBean.setPackagesToScan("com.airline");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getAdditionalProperties());
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }*/

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }

	//Entity Manager
	@Autowired
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource, AdditionalPropertiesConfig jpaProperties) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();

		em.setDataSource (dataSource);
		em.setPackagesToScan ("com.airline");
		//em.setJpaProperties (getAdditionalProperties());
		em.setJpaProperties (jpaProperties.getAdditionalProperties ());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
		em.setJpaVendorAdapter (vendorAdapter);
		return em;
	}

	@Bean(name = "entityManager")
	public EntityManager getEntityManager (EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager ();
	}




 /* @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ApplicationStarter.class);
  }*/
	//TODO
	// add flyway
	// add safe delete
	// check field controller @PathVariable
	// close password
	// add role 1:12:15  26.03.2020
    // add lock annotation on update method 26.03.2020
}
