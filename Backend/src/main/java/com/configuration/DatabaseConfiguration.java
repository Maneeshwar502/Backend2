package com.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.model.User;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
	public DatabaseConfiguration(){
		System.out.println("database configuration instantiated");
	}
	    @Autowired
	    @Bean(name="sessionFactory")
		public SessionFactory sessionFactory() {
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			lsf.addProperties(hibernateProperties);
			Class classes[]=new Class[]{User.class};//class objects of all entities
			System.out.println("user table created");
		    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
		}
	    @Autowired
		@Bean(name="dataSource")
		public DataSource getDataSource() {
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		    dataSource.setUsername("dtproject2");
		    dataSource.setPassword("123456");
		    System.out.println("oracle database connected");
		    return dataSource;
		    
		}
	    @Autowired
		@Bean(name="transactionManager")
		public HibernateTransactionManager hibTransManagement(){  
			System.out.println("Transaction");
			return new HibernateTransactionManager(sessionFactory());
			
		}
	}
