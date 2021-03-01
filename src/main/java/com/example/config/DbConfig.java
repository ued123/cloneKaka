package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/*
    xml 대신하여 어노테이션을 통해서 db관련 라이브러리를 구현한 이유는 별거 없이 어노테이션을 이용한 빈 등록이 대세 이기때문에 공부할겸 사용하였음.
    xml로도 설정할 수 있다는것을 인지하고 있자
    xml보다 어노테이션을 통해 등록하는게 어떤 장점인지는 좀더 공부 해봐야 할 것 같다

    springboot application 구동시 configuration 어노테이션 먼저 읽는다.
    @PropertySource : properties 설정 같은 경우 기본 파일이 application.properties 이기 때문에 따로 기입하지 않아도 알아서 읽을 수 있다
    @EnableJpaRepositories : 어노테이션은 JpaRepository 에 대한 설정정보를 자동적으로 로딩하고 이 정보를 토대로 Repository 빈을 등록하는 역할을 한다

 */
@Configuration
//@PropertySource(value = "classpath : application.properties")
@EntityScan(basePackages = {"com.example.dao"})
@EnableJpaRepositories(basePackages = {"com.example.dao"})
public class DbConfig {

    /*
        properties 에 정의한 디비 정보를 @value 어노테이션을 통해 가져올 수 있음
     */
    @Value("${database.driver}")
    String databaseDriver;

    @Value("${database.url}")
    String databaseUrl;

    @Value("${database.username}")
    String databaseId;

    @Value("${database.password}")
    String databasePw;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseId);
        dataSource.setPassword(databasePw);
        return dataSource;
    }

    /*
        Hibernate를 사용하기 위해서는 Configuration에 다음 2가지가 등록되어야지 됩니다.
        LocalSessionFactoryBean : SessionFactory에 대한 Factory Bean입니다. SessionFactory를 생성하는 객체를 등록시켜줍니다. 이는 Spring에서 사용할 DataSource와 Entity가 위치한 Package들에 대한 검색을 모두 포함하게 됩니다.
        HibernateTransactionManager : PlatformTransactionManager를 구현한 Hibernate용 TransactionManager를 등록해야지 됩니다. 이는 Spring에서 @EnableTransactionManager와 같이 사용되어 @Transactional annotation을 사용할 수 있게 됩니다.

     */

    /*
        Hibernate(Jpa)에서 SessionFactoryBean과 동일한 역활을 담당하는 FactoryBean입니다.
        EntityManagerFactory를 생성하는 FactoryBean형태입니다.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.example.*" });

//        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
//        properties.put("hibernate.physical_naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

        /*
            JPA 규약과 Hibernate간의 Adapter 입니다. 특별히 설정할 내용은 존재하지 않고, showSQL 정도의 설정만이 존재합니다.
         */
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    /*
        JPA를 지원하는 TransactionManager를 등록합니다. DataSource와 EntityManagerFactory를 등록시켜주면 됩니다.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

        /*
            PersistenceExceptionTranslationPostProcessor : JPA 관련된 예외를 좀더 다양하게 던질 수 있도록 하는 라이브러리?
         */
        @Bean
        public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
            return new PersistenceExceptionTranslationPostProcessor();
        }

        Properties additionalProperties() {
            Properties properties = new Properties();
            //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

            //        #spring.jpa.properties.hibernate.show_sql=true
            //#spring.jpa.properties.hibernate.format_sql=true
            //#spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            /*properties.setProperty("physical_naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
            properties.setProperty("spring.jpa.properties.hibernate.show_sql","true");
            properties.setProperty("spring.jpa.properties.hibernate.format_sql","true");
            properties.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation","true");*/

            return properties;
        }
}
