package com.pru.token.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.KeyStore;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class TokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenServiceApplication.class, args);
	}
	
	@Value("${spring.mail.username}")
    private String emailUsername;
	
	public static final String DEFAULT_KEY_STORE_PASSWORD = "mnbvcxz";
    
    @Value("${spring.mail.password}")
    private String emailPassword;
    
    @Value("${spring.mail.port}")
    private String emailPort;
    
    @Value("${spring.mail.host}")
    private String emailHost;

    @Bean
    public JavaMailSender javaMailSender() throws Exception {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(emailUsername);
        javaMailSender.setPassword(emailPassword);
        javaMailSender.setHost(emailHost);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setPort(Integer.parseInt(emailPort));

        // Set SSL properties
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
        
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(this.getClass().getClassLoader().getResourceAsStream("/common.jks"), DEFAULT_KEY_STORE_PASSWORD.toCharArray());
        trustManagerFactory.init(keyStore);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }
    
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET","POST","PUT", "DELETE");
			}
		};
	}

}
