package fr.texturecraft.service.impl;

import fr.texturecraft.api.GenerationApiPort;
import fr.texturecraft.dto.PromptRequest;
import fr.texturecraft.dto.PromptResponse;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import static fr.texturecraft.constantes.PromptConstantes.COULEUR;
import static fr.texturecraft.constantes.PromptConstantes.DESCRIPTION;
import static fr.texturecraft.constantes.PromptConstantes.ESPACE;
import static fr.texturecraft.constantes.PromptConstantes.FOND;
import static fr.texturecraft.constantes.PromptConstantes.MATERIAU;
import static fr.texturecraft.constantes.PromptConstantes.MINECRAFT;
import static fr.texturecraft.constantes.PromptConstantes.NOM_TYPE;
import static fr.texturecraft.constantes.PromptConstantes.OPACITE;
import static fr.texturecraft.constantes.PromptConstantes.STYLE_PIXEL_ART;
import static fr.texturecraft.constantes.PromptConstantes.SYMETRIE;
import static fr.texturecraft.constantes.PromptConstantes.TYPE_BLOCK;
import static fr.texturecraft.constantes.PromptConstantes.TYPE_ITEM;
import static fr.texturecraft.constantes.PromptConstantes.VIRGULE;
import static fr.texturecraft.constantes.PromptConstantes.VUE;

@Service
public class GenererTextureServiceImpl implements GenererTextureService {
  private final GenerationApiPort generationApiPort;

  public GenererTextureServiceImpl(GenerationApiPort generationApiPort) {
    this.generationApiPort = generationApiPort;
  }

  @Override
  public byte[] genererTexture(GenerationForm generationForm) {
    PromptResponse promptResponse =
        generationApiPort.genererTexture(construirePromptRequest(generationForm));

    return convertirImageBase64ToTableauBytes(promptResponse.getImageBase64());
  }

  private PromptRequest construirePromptRequest(GenerationForm generationForm) {
    PromptRequest promptRequest = new PromptRequest();
    promptRequest.setModel(ModelEnum.getName(generationForm.getModelEnum()));
    promptRequest.setPrompt(construirePromptSelonGenerationForm(generationForm));
    promptRequest.setNegativePrompt("");
    promptRequest.setSeed("6224");

    return promptRequest;
  }

  private String construirePromptSelonGenerationForm(GenerationForm generationForm) {
    StringBuilder prompt = new StringBuilder(MINECRAFT);
    prompt.append(ESPACE);

    // Ajout des tags obligatoires
    prompt.append(TYPE_BLOCK.contains(generationForm.getTypeObjet()) ? TYPE_BLOCK : TYPE_ITEM);
    prompt.append(MATERIAU).append(generationForm.getMateriau()).append(VIRGULE);
    prompt.append(NOM_TYPE).append(generationForm.getNomType()).append(VIRGULE);
    prompt.append(COULEUR).append(generationForm.getCouleur()).append(VIRGULE);
    prompt.append(DESCRIPTION).append(generationForm.getDescription()).append(VIRGULE);

    construirePromptPourTagsOptionnels(prompt, generationForm);

    prompt.append(STYLE_PIXEL_ART);

    return prompt.toString();
  }

  private void construirePromptPourTagsOptionnels(StringBuilder prompt, GenerationForm generationForm) {
    Map<String, String> tagsOptionnels = new LinkedHashMap<>();

    // Maintient de l'ordre des tags optionnels
    tagsOptionnels.put(FOND, generationForm.getFond());
    tagsOptionnels.put(OPACITE, generationForm.getOpacite());
    tagsOptionnels.put(VUE, generationForm.getVue());
    tagsOptionnels.put(SYMETRIE, generationForm.getSymetrie());

    tagsOptionnels.entrySet().stream()
        .filter(tag ->
            StringUtils.isNotEmpty(tag.getValue()))
        .forEach(tag ->
            prompt.append(tag.getKey()).append(tag.getValue()).append(VIRGULE));
  }

  private byte[] convertirImageBase64ToTableauBytes(String base64Image) {
    return Base64.getDecoder().decode(base64Image);
  }
}
