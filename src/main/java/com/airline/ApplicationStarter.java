package com.airline;

import com.airline.config.core.DatabaseConfig;
import com.airline.config.core.JdbcTemplateConfig;
import com.airline.config.swagger.SwaggerConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.airline"},
        exclude = {
                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })

@Import({
        DatabaseConfig.class,
        JdbcTemplateConfig.class,
        SwaggerConfig.class,
})

//@EntityScan(basePackages = { "com.airline.entity" })
public class ApplicationStarter extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        // Fix Postgres JPA Error:
        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan("com.airline");
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }
    //Entity Manager
    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.airline");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }



 /* @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ApplicationStarter.class);
  }*/

}
