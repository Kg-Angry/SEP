package com.sep.koncentratorPlacanja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class KoncentratorPlacanjaApplication {

	static {

		// Kopirati sertifikate u C
		// Nalaze se target/class
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", "c://TrustStore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "kgangry");
		System.setProperty("javax.net.ssl.keyStore", "c://keystore.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "kgangry");

		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(KoncentratorPlacanjaApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
//		RestTemplate rt = new RestTemplate();
//		rt.setRequestFactory( new HttpComponentsClientHttpRequestFactory() );
		return new RestTemplate();
	}
}
