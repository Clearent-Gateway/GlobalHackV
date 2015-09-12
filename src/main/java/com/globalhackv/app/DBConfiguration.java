package com.globalhackv.app;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.Violation;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jwillard on 9/12/2015.
 */
@Configuration
@ComponentScan("com.globalhackv")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.globalhackv.app.repository")
public class DBConfiguration {


    private static final String MSSQL_SHOW_SQL = "false";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String ORG_HIBERNATE_DIALECT_HSQL_DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String ORG_HIBERNATE_DIALECT_SQL_SERVER_DIALECT = "org.hibernate.dialect.SQLServerDialect";

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment env;
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mssql")
    public DataSource gatewayMsSqlDataSource() {
        return DataSourceBuilder.create().build();
    }


//    @Bean
//    public AnnotationSessionFactoryBean msSqlSessionFactory() {
//        AnnotationSessionFactoryBean factoryBean = new AnnotationSessionFactoryBean();
//        factoryBean.setDataSource(gatewayMsSqlDataSource());
//        Class<?>[] annotatedClasses = new Class<?>[] { Violation.class, Citation.class };
//        factoryBean.setAnnotatedClasses(annotatedClasses);
//        factoryBean.setHibernateProperties(getMsSqlHibernateProperties());
//        return factoryBean;
//    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        return properties;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(gatewayMsSqlDataSource());
       entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
       entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

       entityManagerFactoryBean.setJpaProperties(hibProperties());

       return entityManagerFactoryBean;
    }
}
