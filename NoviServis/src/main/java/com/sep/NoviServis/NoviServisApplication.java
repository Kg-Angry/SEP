package com.sep.NoviServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableEurekaClient
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class NoviServisApplication {

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
		SpringApplication.run(NoviServisApplication.class, args);
	}

}
