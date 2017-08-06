package com.saas.edu.config.db;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class DataSourceConfiguration {
	
	@Bean(name="dataSource")
	public DataSource getSaasCoreDataSource(){
		return getDataSource("java:/comp/env/jdbc/saas_core_db");
	}

	private DataSource getDataSource(String jndiName) {
		DataSource dataSource = null;
		JndiTemplate jndiTemplate = new JndiTemplate();
		try {
			dataSource = (DataSource) jndiTemplate.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			dataSource = new DriverManagerDataSource();
		}
		return dataSource;
	}

}
