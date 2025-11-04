package fr.texturecraft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptResponse {
  private String imageBase64;
  private String model;
}
