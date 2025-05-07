package com.bookstore.manager_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;

@SpringBootApplication
public class ManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerServiceApplication.class, args);
	}
	
	@Configuration
	public class RestTemplateConfig {

	    @Bean
	    public RestTemplate restTemplate() {
	        PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();

	        CloseableHttpClient httpClient = HttpClients.custom()
	                .setConnectionManager(poolingConnManager)
	                .build();

	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	        factory.setHttpClient(httpClient);

	        return new RestTemplate(factory);
	    }
	}

}
