package fr.texturecraft.service;

import fr.texturecraft.dto.ErrorDetails;
import fr.texturecraft.dto.FormatErreurInattendueException;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.exception.CommunicationAPIPythonException;
import fr.texturecraft.exception.ErreurInterneAPIPythonException;
import fr.texturecraft.exception.ValidationRequeteAPIPythonException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import static fr.texturecraft.constantes.ApiConstantes.URL_API_PY_GENERATION_TEXTURE;

@RestController
public class PyGenerationRestTemplateService {
  private final RestTemplate restTemplate;

  public PyGenerationRestTemplateService() {
    this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
  }

  public PromptResponse genererTexture(PromptRequest promptRequest) {
    HttpEntity<PromptRequest> request = new HttpEntity<>(promptRequest);

    try {
      ResponseEntity<PromptResponse> response =
          restTemplate.exchange(URL_API_PY_GENERATION_TEXTURE, HttpMethod.POST, request, PromptResponse.class);

      return response.getBody();

    } catch (HttpClientErrorException e) {
      throw new ValidationRequeteAPIPythonException(e.getMessage());
    } catch (HttpServerErrorException e) {
      try {
        String errorJson = e.getResponseBodyAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorDetails errorDetails = objectMapper.readValue(errorJson, ErrorDetails.class);
        throw new ErreurInterneAPIPythonException(errorDetails);
      } catch (Exception jsonE) {
        throw new FormatErreurInattendueException(jsonE.getMessage());
      }
    } catch (Exception e) {
      throw new CommunicationAPIPythonException(e.getMessage());
    }
  }

  ClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 500;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
        HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectionRequestTimeout(timeout*1000);
    clientHttpRequestFactory.setConnectTimeout(timeout*2000);
    clientHttpRequestFactory.setReadTimeout(timeout*3000);
    return clientHttpRequestFactory;
  }
}
