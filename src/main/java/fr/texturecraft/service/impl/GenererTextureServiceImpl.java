package fr.texturecraft.service.impl;

import fr.texturecraft.api.GenerationApiPort;
import fr.texturecraft.dto.GenerationResponseDTO;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import org.springframework.stereotype.Service;

@Service
public class GenererTextureServiceImpl implements GenererTextureService {
  private final GenerationApiPort generationApiPort;

  public GenererTextureServiceImpl(GenerationApiPort generationApiPort) {
    this.generationApiPort = generationApiPort;
  }

  @Override
  public GenerationResponseDTO genererTexture(GenerationForm generationForm) {
    PromptResponse promptResponse =
        generationApiPort.genererTexture(construirePromptRequest(generationForm));

    return new GenerationResponseDTO(
        promptResponse.imageBase64Cible(),
        promptResponse.imageBase64Affichage()
    );
  }

  private PromptRequest construirePromptRequest(GenerationForm generationForm) {
    return new PromptRequest(
        ModelEnum.getName(generationForm.modelEnum()),
        generationForm.resolution(),
        generationForm.construirePromptSelonGenerationForm(),
        "",
        "6224");
  }
}
