package com.onlyas.multidata.config;

import com.onlyas.multidata.MultidataApplication;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.onlyas.multidata.dao.two", sqlSessionFactoryRef = "twoSqlSessionFactory",nameGenerator = MultidataApplication.SpringBeanNameGenerator.class)
public class TwoDataSourceConfig {
    @Autowired
    @Qualifier("dataSourceTwo")
    private DataSource dataSourceTwo;

    @Bean(name = "twoTransactionManager")
    public DataSourceTransactionManager twoTransactionManager() {
        return new DataSourceTransactionManager(dataSourceTwo);
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory twoSqlSessionFactory(@Qualifier("dataSourceTwo") DataSource dataSourceTwo)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceTwo);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/onlyas/multidata/dao/two/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.onlyas.multidata.domain.two");
        return sessionFactory.getObject();
    }
}
