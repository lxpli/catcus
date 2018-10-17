package com.work.manager.config;

import java.io.Serializable;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="common")
public class CommonConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1838983360850574242L;
	
	private String token;
	private int supplyDate;
	private String tmpLocation;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	public int getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(int supplyDate) {
		this.supplyDate = supplyDate;
	}
	
	public String getTmpLocation() {
		return tmpLocation;
	}

	public void setTmpLocation(String tmpLocation) {
		this.tmpLocation = tmpLocation;
	}

	@Override
	public String toString() {
		return "CommonConfig [token=" + token + ", supplyDate=" + supplyDate
				+ "]";
	}
	
	@Bean
    MultipartConfigElement multipartConfigElement() {
       MultipartConfigFactory factory = new MultipartConfigFactory();
       factory.setLocation(tmpLocation);
       return factory.createMultipartConfig();
    }
	
}
