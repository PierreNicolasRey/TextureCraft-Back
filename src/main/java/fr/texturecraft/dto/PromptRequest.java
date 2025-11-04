package fr.texturecraft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptRequest {
  private String prompt;
  private String model;
  private String negativePrompt;
  private String seed;
}
