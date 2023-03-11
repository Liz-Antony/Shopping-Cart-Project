package ca.sheridancollege.antonye.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ProductDatabaseConfiguration {
	public class ProductsDatabaseConfiguration{
		//Used in the ClubDatabaseAccess class to submit JDBC parametric query Strings
		@Bean
		public NamedParameterJdbcTemplate namedParemterJdbcTemplate(DataSource dataSource){
		return new NamedParameterJdbcTemplate(dataSource);


}
	}
}
