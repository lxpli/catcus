package com.work.manager.config;

import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfig {
	
	private String host;
	private int port;
	private int timeout;
	private int expire;
	private String password;
	public String getHost() {
		return host;
	}
	public void setHost(String ip) {
		this.host = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RedisConfig [host=" + host + ", port=" + port + "]";
	}
	
	@Bean
	public RedisManager getRedisManager(){
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(getHost());
		redisManager.setPort(port);
		redisManager.setTimeout(getTimeout());
		redisManager.setExpire(getExpire());
		redisManager.setPassword(getPassword());
		return redisManager;
	}
	
	@Bean
	public RedisSessionDAO getRedisSessionDAO(RedisManager redisManager){
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager);
		redisSessionDAO.setKeyPrefix("shiro:session:");
		return redisSessionDAO;
	}
	
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig config = new JedisPoolConfig(); 
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setTimeout(timeout);
		factory.setPassword(password);
        factory.setPoolConfig(config);
        return factory;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ?> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
