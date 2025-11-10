package fr.texturecraft.model;

import fr.texturecraft.enums.ModelEnum;
import io.micrometer.common.util.StringUtils;

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

public record GenerationForm(ModelEnum modelEnum,
                             String resolution,
                             String typeObjet,
                             String materiau,
                             String nomType,
                             String couleur,
                             String description,
                             String fond,
                             String opacite,
                             String vue,
                             String symetrie) {

  public String construirePromptSelonGenerationForm() {
    StringBuilder prompt = new StringBuilder(MINECRAFT);
    prompt.append(ESPACE);

    // Ajout des tags obligatoires
    prompt.append(TYPE_BLOCK.contains(this.typeObjet()) ? TYPE_BLOCK : TYPE_ITEM);
    prompt.append(MATERIAU).append(this.materiau()).append(VIRGULE);
    prompt.append(NOM_TYPE).append(this.nomType()).append(VIRGULE);
    prompt.append(COULEUR).append(this.couleur()).append(VIRGULE);
    prompt.append(DESCRIPTION).append(this.description()).append(VIRGULE);

    construirePromptPourTagsOptionnels(prompt);

    prompt.append(STYLE_PIXEL_ART);

    return prompt.toString();
  }

  private void construirePromptPourTagsOptionnels(StringBuilder prompt) {
    Map<String, String> tagsOptionnels = new LinkedHashMap<>();

    // Maintient de l'ordre des tags optionnels
    tagsOptionnels.put(FOND, this.fond());
    tagsOptionnels.put(OPACITE, this.opacite());
    tagsOptionnels.put(VUE, this.vue());
    tagsOptionnels.put(SYMETRIE, this.symetrie());

    tagsOptionnels.entrySet().stream()
        .filter(tag ->
            StringUtils.isNotEmpty(tag.getValue()))
        .forEach(tag ->
            prompt.append(tag.getKey()).append(tag.getValue()).append(VIRGULE));
  }
}
