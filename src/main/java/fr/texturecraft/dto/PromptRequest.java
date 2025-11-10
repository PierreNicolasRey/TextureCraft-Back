package fr.texturecraft.dto;

public record PromptRequest(String model,
                            String resolution,
                            String prompt,
                            String negativePrompt,
                            String seed) {}
