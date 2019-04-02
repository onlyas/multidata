package com.onlyas.multidata.config;

import com.github.pagehelper.PageInterceptor;
import com.onlyas.multidata.MultidataApplication;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.onlyas.multidata.dao.one", sqlSessionFactoryRef = "oneSqlSessionFactory", nameGenerator = MultidataApplication.SpringBeanNameGenerator.class)
public class OneDataSourceConfig {

    @Autowired
    @Qualifier("dataSourceOne")
    private DataSource dataSourceOne;

    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager oneTransactionManager() {
        return new DataSourceTransactionManager(dataSourceOne);
    }

    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory oneSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceOne);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/onlyas/multidata/dao/one/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.onlyas.multidata.domain.one");

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否分页合理化
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{interceptor});

        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate oneSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(oneSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }
}
