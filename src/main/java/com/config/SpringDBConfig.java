package com.config;

import javax.sql.DataSource;

/*import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/*import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;*/
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration

@ComponentScan("com.config*")


public class SpringDBConfig {
	
	//@Autowired
		//Environment environment;
		
		private final String URL = "jdbc:edb://10.10.10.235:5444/aiims_bibinagar";
		private final String USER = "aiimsbibinagar";
		private final String DRIVER = "com.edb.Driver";
		private final String PASSWORD = "a11msb!b!n@g@r*$";
		
		@Bean
		DataSource dataSource() {
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setUrl(URL);
			driverManagerDataSource.setUsername(USER);
			driverManagerDataSource.setPassword(PASSWORD);
			driverManagerDataSource.setDriverClassName(DRIVER);
			return driverManagerDataSource;
		}

		 @Bean
		  public PlatformTransactionManager transactionManager()
		  {
		      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		      transactionManager.setDataSource(dataSource());
		      return transactionManager;
		  }
	

}