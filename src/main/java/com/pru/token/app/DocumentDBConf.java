//package com.pru.token.app;
//
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import com.mongodb.MongoClientSettings;
//
//@Configuration
//public class DocumentDBConf {
//	
//	
//	public static final String KEY_STORE_TYPE = "/tmp/certs/rds-truststore.jks";
//    public static final String DEFAULT_KEY_STORE_PASSWORD = "mnbvcxz";
//
//
//        @Bean
//        public MongoClientSettings mongoClientSettings() {
//        	System.out.println("mongo clint setting called");
//             setSslProperties();
//	     return MongoClientSettings.builder()
//                    .applyToSslSettings(builder -> builder.enabled(true))
//                    .build();
//        }
//
//        private static void setSslProperties() {
//    	      System.setProperty("javax.net.ssl.trustStore", KEY_STORE_TYPE);
//    	      System.setProperty("javax.net.ssl.trustStorePassword",           
//                    DEFAULT_KEY_STORE_PASSWORD);
//        }
//        
//        @Bean
//        public MongoPropertiesClientSettingsBuilderCustomizer mongoPropertiesCustomizer(final MongoProperties properties,final Environment environment) {
//			System.out.println("mongo mpcsbc");
//        	return new MongoPropertiesClientSettingsBuilderCustomizer(properties,environment);
//        }
//}