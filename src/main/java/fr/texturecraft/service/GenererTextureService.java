package fr.texturecraft.service;

import fr.texturecraft.dto.GenerationResponseDTO;
import fr.texturecraft.model.GenerationForm;

public interface GenererTextureService {
  public GenerationResponseDTO genererTexture(GenerationForm generationForm);
}
