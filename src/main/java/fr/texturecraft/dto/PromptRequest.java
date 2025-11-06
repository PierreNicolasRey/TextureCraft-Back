package fr.texturecraft.dto;

public class PromptRequest {
  private String prompt;
  private String model;
  private String negativePrompt;
  private String seed;

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getNegativePrompt() {
    return negativePrompt;
  }

  public void setNegativePrompt(String negativePrompt) {
    this.negativePrompt = negativePrompt;
  }

  public String getSeed() {
    return seed;
  }

  public void setSeed(String seed) {
    this.seed = seed;
  }
}
