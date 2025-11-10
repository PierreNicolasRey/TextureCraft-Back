package fr.texturecraft.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.texturecraft.dto.ErrorDetails;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.exception.CommunicationAPIPythonException;
import fr.texturecraft.exception.ErreurInterneAPIPythonException;
import fr.texturecraft.exception.FormatErreurInattendueException;
import fr.texturecraft.exception.ValidationRequeteAPIPythonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static fr.texturecraft.constantes.ApiConstantes.URL_API_PY_GENERATION_TEXTURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PyGenerationRestTemplateServiceTest {
  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private PyGenerationRestTemplateService pyGenerationRestTemplateService;

  @Test
  void genererTextureSucces() {
    // GIVEN
    PromptRequest promptRequest = new PromptRequest("a prompt", "final", "", "");

    PromptResponse promptResponseAttendue = new PromptResponse("aW1hZ2UgZW5jb2TDqSBlbiBiYXNlIDY0","final");

    ResponseEntity<PromptResponse> response = new ResponseEntity<>(promptResponseAttendue, HttpStatus.OK);

    when(restTemplate.exchange(eq(URL_API_PY_GENERATION_TEXTURE),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(PromptResponse.class)))
        .thenReturn(response);

    // WHEN
    PromptResponse promptResponseObtenue = pyGenerationRestTemplateService.genererTexture(promptRequest);

    // THEN
    Assertions.assertAll(() -> {
      verify(restTemplate, times(1))
          .exchange(eq(URL_API_PY_GENERATION_TEXTURE),
              eq(HttpMethod.POST),
              any(HttpEntity.class),
              eq(PromptResponse.class));

      assertNotNull(promptResponseObtenue);
      assertEquals(promptResponseAttendue.model(), promptResponseObtenue.model());
      assertEquals(promptResponseAttendue.imageBase64(), promptResponseObtenue.imageBase64());
    });
  }

  @Test
  void genererTextureValidationRequeteAPIPythonExceptionTest() {
    // GIVEN
    when(restTemplate.exchange(
        eq(URL_API_PY_GENERATION_TEXTURE),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(PromptResponse.class)))
        .thenThrow(new HttpClientErrorException(HttpStatus.UNPROCESSABLE_CONTENT, "Requête invalide"));

    // THEN
    Assertions.assertThrows(ValidationRequeteAPIPythonException.class, () -> {
      // WHEN
      pyGenerationRestTemplateService.genererTexture(
          new PromptRequest("a prompt", "modele_inconnu", "", ""));
    });
  }

  @Test
  void genererTextureErreurInterneAPIPythonExceptionTest() throws JsonProcessingException {
    // GIVEN
    ErrorDetails errorDetails = new ErrorDetails("error", "Erreur serveur", 500);

    String erreurInterneAPIPythonExceptionMessage = "Erreur interne de l'API Python : \n"
        + "Code : " + errorDetails.code() + " \n"
        + "Message : " + errorDetails.message();

    ObjectMapper objectMapper = new ObjectMapper();

    HttpServerErrorException mockException = new HttpServerErrorException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Internal Server Error",
        objectMapper.writeValueAsString(errorDetails).getBytes(),
        java.nio.charset.StandardCharsets.UTF_8
    );

    when(restTemplate.exchange(
        eq(URL_API_PY_GENERATION_TEXTURE),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(PromptResponse.class)))
        .thenThrow(mockException);

    // THEN
    ErreurInterneAPIPythonException thrownException =
        Assertions.assertThrows(ErreurInterneAPIPythonException.class, () -> {
          // WHEN
          pyGenerationRestTemplateService.genererTexture(
            new PromptRequest("a prompt", "final", "", ""));
      });
    assertEquals(thrownException.getMessage(), erreurInterneAPIPythonExceptionMessage);
  }

  @Test
  void genererTextureFormatErreurInattendueExceptionTest() {
    // GIVEN
    when(restTemplate.exchange(
        eq(URL_API_PY_GENERATION_TEXTURE),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(PromptResponse.class)))
        .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur serveur"));

    // THEN
    Assertions.assertThrows(FormatErreurInattendueException.class, () -> {
      // WHEN
      pyGenerationRestTemplateService.genererTexture(
          new PromptRequest("a prompt", "final", "", ""));
    });
  }

  @Test
  void genererTextureCommunicationAPIPythonExceptionTest() {
    // GIVEN
    when(restTemplate.exchange(
        eq(URL_API_PY_GENERATION_TEXTURE),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(PromptResponse.class)))
        .thenThrow(new RuntimeException("Connexion refusée"));

    // THEN
    Assertions.assertThrows(CommunicationAPIPythonException.class, () -> {
      // WHEN
      pyGenerationRestTemplateService.genererTexture(
          new PromptRequest("a prompt", "final", "", ""));
    });
  }
}