//package com.pru.token.app;
//
//import java.io.InputStream;
//import java.security.KeyStore;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManagerFactory;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.connection.SslSettings;
//
//@Profile("dev")
//@Configuration
//public class DocumentDBConf {
//	
//	@Value("${spring.data.mongodb.uri}")
//    private String mongoUri;
//
//    @Value("${spring.data.mongodb.ssl.enabled}")
//    private boolean sslEnabled;
//
//    @Value("${spring.data.mongodb.ssl.trust-store}")
//    private String trustStore;
//
//    @Value("${spring.data.mongodb.ssl.trust-store-password}")
//    private String trustStorePassword;
//
//    @Bean
//    public MongoClient mongoClient() throws Exception {
//        ConnectionString connectionString = new ConnectionString(mongoUri);
//        MongoClientSettings.Builder builder = MongoClientSettings.builder()
//                .applyConnectionString(connectionString);
//        if (sslEnabled) {
//            
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            try (InputStream is = getClass().getClassLoader().getResourceAsStream(this.trustStore)) {
//                trustStore.load(is, this.trustStorePassword.toCharArray());
//            }
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            tmf.init(trustStore);
//            sslContext.init(null, tmf.getTrustManagers(), null);
//            SslSettings sslSettings = SslSettings.builder()
//            		.context(sslContext)
//                    .enabled(true)
//                    .build();
//            builder.applyToSslSettings(builderq -> builderq.applySettings(sslSettings));
//            
//        }
//        MongoClientSettings mongoClientSettings = builder.build();
//        return MongoClients.create(mongoClientSettings);
//    }
//
//}