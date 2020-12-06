package csc460.SqlPlusShoppingMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class SqlPlusShoppingMallApplication {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
		ds.setUrl("jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle");
		ds.setUsername("chaonengquan");
		ds.setPassword("a6562");
		return ds;
	}

	public static void main(String[] args) {
		SpringApplication.run(SqlPlusShoppingMallApplication.class, args);
	}

}
