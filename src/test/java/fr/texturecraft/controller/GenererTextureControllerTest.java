package fr.texturecraft.controller;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.mapper.GenerationFormMapper;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static fr.texturecraft.GenerationFormTestUtils.creerGenerationForm;
import static fr.texturecraft.GenerationFormTestUtils.creerGenerationFormDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenererTextureControllerTest {

  @Mock
  private GenererTextureService genererTextureService;
  @Mock
  private GenerationFormMapper generationFormMapper;

  @InjectMocks
  private GenererTextureController genererTextureController;

  private final GenerationFormDTO generationFormDTO;
  private final GenerationForm generationForm;

  GenererTextureControllerTest() {
    generationFormDTO = creerGenerationFormDTO("block", false,
        false, false, false);
    generationForm = creerGenerationForm("block", false,
        false, false, false);
  }

  @Test
  void verifierAppelQuandGenererTextureTest() {
    // GIVEN
    byte[] bytes = new byte[2];

    when(generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO))
        .thenReturn(generationForm);
    when(genererTextureService.genererTexture(generationForm))
        .thenReturn(bytes);

    // WHEN
    ResponseEntity<byte[]> response = genererTextureController.genererTexture(generationFormDTO);

    // THEN
    Assertions.assertAll(() -> {
      verify(generationFormMapper, times(1))
          .mapperGenerationFormDTOToGenerationForm(generationFormDTO);
      verify(genererTextureService, times(1))
          .genererTexture(generationForm);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(bytes, response.getBody());
      assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
      assertEquals(bytes.length, response.getHeaders().getContentLength());
    });
  }

  @Test
  void verifierPropagationExceptionQuandServiceEchoueTest() {
    // GIVEN
    when(generationFormMapper.mapperGenerationFormDTOToGenerationForm(any()))
        .thenReturn(generationForm);

    when(genererTextureService.genererTexture(generationForm))
        .thenThrow(new RuntimeException("Erreur du service Python."));

    // THEN
    Assertions.assertThrows(RuntimeException.class, () -> {
      // WHEN
      genererTextureController.genererTexture(generationFormDTO);
    });
    verify(genererTextureService, times(1))
        .genererTexture(generationForm);
  }
}