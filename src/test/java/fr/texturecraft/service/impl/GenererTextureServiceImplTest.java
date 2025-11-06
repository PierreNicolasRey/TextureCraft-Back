package fr.texturecraft.service.impl;

import fr.texturecraft.api.GenerationApiPort;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.model.GenerationForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.texturecraft.GenerationFormTestUtils.creerGenerationForm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenererTextureServiceImplTest {
  @Mock
  private GenerationApiPort generationApiPort;

  @InjectMocks
  private GenererTextureServiceImpl genererTextureService;

  private final ArgumentCaptor<PromptRequest> promptRequestCaptor = ArgumentCaptor.forClass(PromptRequest.class);

  @Test
  void genererTextureSansTagsOptionnelsTest() {
    // GIVEN
    String promptAttendu = "minecraft block : material : wood, type : nom_texture, color : brown, " +
        "features : une description, style : pixel art";

    GenerationForm generationForm = creerGenerationForm("block", false, false,
        false,false);

    PromptResponse promptResponseAttendue = new PromptResponse();
    promptResponseAttendue.setModel("final");
    promptResponseAttendue.setImageBase64("aW1hZ2UgZW5jb2TDqSBlbiBiYXNlIDY0");

    when(generationApiPort.genererTexture(any(PromptRequest.class)))
        .thenReturn(promptResponseAttendue);

    // WHEN
    genererTextureService.genererTexture(generationForm);

    // THEN
    Assertions.assertAll(() -> {
      verify(generationApiPort, times(1)).genererTexture(promptRequestCaptor.capture());

      PromptRequest capturedRequest = promptRequestCaptor.getValue();

      assertEquals(promptResponseAttendue.getModel(), capturedRequest.getModel());
      assertEquals(promptAttendu, capturedRequest.getPrompt());
    });
  }

  @Test
  void genererTextureAvecTagsOptionnelsTest() {
    // GIVEN
    String promptAttendu = "minecraft block : material : wood, type : nom_texture, color : brown, " +
        "features : une description, background : transparent, opacity : semi-transparent, view : top, symmetry : radial, style : pixel art";

    GenerationForm generationForm = creerGenerationForm("block", true, true,
        true,true);

    PromptResponse promptResponseAttendue = new PromptResponse();
    promptResponseAttendue.setModel("final");
    promptResponseAttendue.setImageBase64("aW1hZ2UgZW5jb2TDqSBlbiBiYXNlIDY0");

    when(generationApiPort.genererTexture(any(PromptRequest.class)))
        .thenReturn(promptResponseAttendue);

    // WHEN
    genererTextureService.genererTexture(generationForm);

    // THEN
    Assertions.assertAll(() -> {
      verify(generationApiPort, times(1)).genererTexture(promptRequestCaptor.capture());

      PromptRequest capturedRequest = promptRequestCaptor.getValue();

      assertEquals(promptResponseAttendue.getModel(), capturedRequest.getModel());
      assertEquals(promptAttendu, capturedRequest.getPrompt());
    });
  }
}