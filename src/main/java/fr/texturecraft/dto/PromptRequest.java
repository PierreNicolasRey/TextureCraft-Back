package fr.texturecraft.dto;

public record PromptRequest(String model,
                            String prompt,
                            String negativePrompt,
                            String seed) {}
