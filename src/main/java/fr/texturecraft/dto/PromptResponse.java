package fr.texturecraft.dto;

public class PromptResponse {
  private String imageBase64;
  private String model;

  public String getImageBase64() {
    return imageBase64;
  }

  public void setImageBase64(String imageBase64) {
    this.imageBase64 = imageBase64;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
