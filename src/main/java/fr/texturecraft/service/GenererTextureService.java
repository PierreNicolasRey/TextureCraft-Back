package fr.texturecraft.service;

import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class GenererTextureService {
  private PyGenerationRestTemplateService pyGenerationRestTemplateService;

  public byte[] genererTexture(GenerationForm generationForm) {
    PromptResponse promptResponse =
        pyGenerationRestTemplateService.genererTexture(construirePromptRequest(generationForm));

    return convertirImageBase64ToTableauBytes(promptResponse.getImageBase64());
  }

  private PromptRequest construirePromptRequest(GenerationForm generationForm) {
    PromptRequest promptRequest = new PromptRequest();
    promptRequest.setModel(ModelEnum.getName(generationForm.getModelEnum()));
    promptRequest.setPrompt(construirePromptSelonGenerationForm(generationForm));

    return promptRequest;
  }

  private String construirePromptSelonGenerationForm(GenerationForm generationForm) {

    return "";
  }

  private byte[] convertirImageBase64ToTableauBytes(String base64Image) {
    return Base64.getDecoder().decode(base64Image);
  }
}
