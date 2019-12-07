package com.sep.koncentratorPlacanja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class KoncentratorPlacanjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoncentratorPlacanjaApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate rt = new RestTemplate();
		rt.setRequestFactory( new HttpComponentsClientHttpRequestFactory() );
		return rt;
	}
}
