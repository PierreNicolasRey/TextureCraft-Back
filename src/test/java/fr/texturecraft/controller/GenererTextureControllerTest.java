package fr.texturecraft.controller;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.dto.GenerationResponseDTO;
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
    GenerationResponseDTO generationResponseDTO =
        new GenerationResponseDTO("aW1hZ2UgZW5jb2TDqSBlbiBiYXNlIDY0", "aW1hZ2UgZW5jb2TDqSBlbiBiYXNlIDY0");

    when(generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO))
        .thenReturn(generationForm);
    when(genererTextureService.genererTexture(generationForm))
        .thenReturn(generationResponseDTO);

    // WHEN
    ResponseEntity<GenerationResponseDTO> response = genererTextureController.genererTexture(generationFormDTO);

    // THEN
    Assertions.assertAll(() -> {
      verify(generationFormMapper, times(1))
          .mapperGenerationFormDTOToGenerationForm(generationFormDTO);
      verify(genererTextureService, times(1))
          .genererTexture(generationForm);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(generationResponseDTO, response.getBody());
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