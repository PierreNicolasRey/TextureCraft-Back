package fr.texturecraft.service.impl;

import fr.texturecraft.api.GenerationApiPort;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class GenererTextureServiceImpl implements GenererTextureService {
  private final GenerationApiPort generationApiPort;

  public GenererTextureServiceImpl(GenerationApiPort generationApiPort) {
    this.generationApiPort = generationApiPort;
  }

  @Override
  public byte[] genererTexture(GenerationForm generationForm) {
    PromptResponse promptResponse =
        generationApiPort.genererTexture(construirePromptRequest(generationForm));

    return convertirImageBase64ToTableauBytes(promptResponse.imageBase64());
  }

  private PromptRequest construirePromptRequest(GenerationForm generationForm) {
    return new PromptRequest(
        ModelEnum.getName(generationForm.modelEnum()),
        generationForm.resolution(),
        generationForm.construirePromptSelonGenerationForm(),
        "",
        "6224");
  }

  private byte[] convertirImageBase64ToTableauBytes(String base64Image) {
    return Base64.getDecoder().decode(base64Image);
  }
}
