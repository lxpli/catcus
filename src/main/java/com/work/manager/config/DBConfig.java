package com.work.manager.config;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;

@Configuration
@ConfigurationProperties(prefix = "spring.db")
@MapperScan(basePackages = "com.work.manager.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class DBConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6461111098318477616L;
    private String url;
    private String name;
    private String pwd;
    private String driver;
    private String connectionProperties;

    public String getConnectionProperties() {
	return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
	this.connectionProperties = connectionProperties;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPwd() {
	return pwd;
    }

    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    public String getDriver() {
	return driver;
    }

    public void setDriver(String driver) {
	this.driver = driver;
    }

    @Override
    public String toString() {
	return "DBConfig [url=" + url + ", name=" + name + ", pwd=" + pwd + ", driver=" + driver + "]";
    }

    @Bean
    public DataSource dataSource() {
	DruidDataSource source = new DruidDataSource();
	source.setUrl(url);
	source.setUsername(name);
	source.setPassword(pwd);
	source.setDriverClassName(driver);
	source.setConnectionProperties(connectionProperties);

	return source;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
	return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	sessionFactory.setDataSource(dataSource);
	sessionFactory.setVfs(SpringBootVFS.class);
	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	sessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
	sessionFactory.setTypeAliasesPackage("com.work.manager.entity");
	sessionFactory.setConfigLocation(resolver.getResource("classpath:/mybatis-config.xml"));
	return sessionFactory.getObject();
    }

    @Bean
    public RestTemplate restTemplate() {
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	return restTemplate;
    }

}
