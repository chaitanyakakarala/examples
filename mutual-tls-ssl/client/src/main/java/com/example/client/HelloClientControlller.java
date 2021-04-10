package com.example.client;


import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class HelloClientControlller {
    //https://medium.com/@niral22/2-way-ssl-with-spring-boot-microservices-2c97c974e83
    @Test
    public void testCallFromClient() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {

        //1. Create a rest template
        RestTemplate restTemplate = new RestTemplate();

        //2. Read the keystore for client certificate
        KeyStore keystore = KeyStore.getInstance("jks");
        ClassPathResource classPathResource = new ClassPathResource("nt-gateway.jks");
        keystore.load(classPathResource.getInputStream(),"secret".toCharArray());

        //3. Load the certificate keystore to ssl socket factory
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
        .loadKeyMaterial(keystore, "secret".toCharArray()).build(),
                NoopHostnameVerifier.INSTANCE);

        //4. make http client using the ssl socket factory and add that to rest template
        HttpClient client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

        //5. Add httpclient to rest client
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate.setRequestFactory(requestFactory);

        String out = restTemplate.getForEntity("https://localhost:8443/api/hello",String.class).getBody();
        System.out.println(out);
    }

}
