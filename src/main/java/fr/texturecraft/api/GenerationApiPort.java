package fr.texturecraft.api;

import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;

public interface GenerationApiPort {
  public PromptResponse genererTexture(PromptRequest promptRequest);
}
