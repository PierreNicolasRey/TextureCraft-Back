package fr.texturecraft.dto;

public record PromptRequest(String model_version,
                            String resolution,
                            String prompt,
                            String negativePrompt,
                            String seed) {}
