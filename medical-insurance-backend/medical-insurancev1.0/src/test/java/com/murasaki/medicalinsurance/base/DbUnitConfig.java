package com.murasaki.medicalinsurance.base;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbUnitConfig {
	@Bean("dbUnitConnection")
    public DatabaseDataSourceConnectionFactoryBean getTestConnection(DataSource dataSource) {
        DatabaseDataSourceConnectionFactoryBean bean = new DatabaseDataSourceConnectionFactoryBean();
        bean.setDataSource(dataSource);
        DatabaseConfigBean databaseConfigBean = new DatabaseConfigBean();
        databaseConfigBean.setMetadataHandler(new MySqlMetadataHandler());
        databaseConfigBean.setDatatypeFactory(new MySqlDataTypeFactory());
        databaseConfigBean.setAllowEmptyFields(true);
        bean.setDatabaseConfig(databaseConfigBean);
        return bean;
    }
}
