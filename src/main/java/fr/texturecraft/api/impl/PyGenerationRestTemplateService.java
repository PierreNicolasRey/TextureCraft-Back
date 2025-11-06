package fr.texturecraft.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.texturecraft.api.GenerationApiPort;
import fr.texturecraft.dto.ErrorDetails;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.exception.CommunicationAPIPythonException;
import fr.texturecraft.exception.ErreurInterneAPIPythonException;
import fr.texturecraft.exception.FormatErreurInattendueException;
import fr.texturecraft.exception.ValidationRequeteAPIPythonException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static fr.texturecraft.constantes.ApiConstantes.URL_API_PY_GENERATION_TEXTURE;

@RestController
public class PyGenerationRestTemplateService implements GenerationApiPort {
  private final RestTemplate restTemplate;
  public PyGenerationRestTemplateService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
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
        ErrorDetails errorDetails = objectMapper.readValue(errorJson.trim(), ErrorDetails.class);
        throw new ErreurInterneAPIPythonException(errorDetails);
      } catch (JsonProcessingException jsonE) {
        throw new FormatErreurInattendueException(jsonE.getMessage());
      }
    } catch (Exception e) {
      throw new CommunicationAPIPythonException(e.getMessage());
    }
  }
}
