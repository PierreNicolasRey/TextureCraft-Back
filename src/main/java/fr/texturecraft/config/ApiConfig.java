package fr.texturecraft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

  private ClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 100;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
        HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectionRequestTimeout(timeout * 10);
    clientHttpRequestFactory.setReadTimeout(timeout * 10000);
    return clientHttpRequestFactory;
  }

  @Bean
  public RestTemplate pyGenerationRestTemplate() {
    return new RestTemplate(getClientHttpRequestFactory());
  }
}
