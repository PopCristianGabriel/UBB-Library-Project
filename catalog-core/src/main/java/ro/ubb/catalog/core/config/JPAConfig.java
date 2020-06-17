package ro.ubb.catalog.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.core.repository.DBrepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by radu.
 */

@Configuration
@EnableJpaRepositories({"ro.ubb.catalog.core.repository"})
@EnableTransactionManagement
//@EnableCaching
public class JPAConfig {


    private static final String PROPERTY_FILENAME = "local\\db.properties"; // Change as needed.

    /**
     * This instance is necessary for Spring to load up the property file and allow access to
     * it through the @Value(${propertyName}) annotation. Also note that this bean must be static
     * in order to work properly with current Spring behavior.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[] { new ClassPathResource(PROPERTY_FILENAME) };
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;
    }

    @Value("${db.jdbcUrl}")
    private String jdbcUrl;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.generateDDL}")
    private Boolean generateDDL;

    /**
     * http://www.baeldung.com/hikaricp
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(Driver.class.getName());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(generateDDL);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ro.ubb.catalog.core.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory());
        return manager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }



    @Bean
    public ro.ubb.catalog.core.repository.DBrepository<Long, Client> DBrepository() throws SQLException, ValidatorException, ParserConfigurationException, IOException {
        System.out.println("dawd");
        EntityManager em = this.entityManager();
        DBrepository<Long,Client> db =   new DBrepository<Long,Client>(Client.class,em);
        return db;

    }

    @Bean
    public ro.ubb.catalog.core.repository.DBrepository<Long, Book> DBrepository2() throws SQLException, ValidatorException, ParserConfigurationException, IOException {
        System.out.println("dawd");
        EntityManager em = this.entityManager();
        DBrepository<Long,Book> db =   new DBrepository<Long,Book>(Book.class,em);
        return db;

    }




}
