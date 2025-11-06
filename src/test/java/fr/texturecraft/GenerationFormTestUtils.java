package fr.texturecraft;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;

public class GenerationFormTestUtils {
  private GenerationFormTestUtils() {}

  public static GenerationForm creerGenerationForm(String typeObjet,
                                                   boolean possedeFond,
                                                   boolean possedeOpacite,
                                                   boolean possedeVue,
                                                   boolean possedeSymetrie) {
    GenerationForm generationForm = new GenerationForm();
    generationForm.setModelEnum(ModelEnum.FINAL);

    generationForm.setTypeObjet(typeObjet);
    generationForm.setMateriau("wood");
    generationForm.setNomType("nom_texture");
    generationForm.setCouleur("brown");
    generationForm.setDescription("une description");

    if (possedeFond) {
      generationForm.setFond("transparent");
    } else {
      generationForm.setFond("");
    }

    if (possedeOpacite) {
      generationForm.setOpacite("semi-transparent");
    } else {
      generationForm.setOpacite("");
    }

    if (possedeVue) {
      generationForm.setVue("top");
    } else {
      generationForm.setVue("");
    }

    if (possedeSymetrie) {
      generationForm.setSymetrie("radial");
    } else {
      generationForm.setSymetrie("");
    }

    return generationForm;
  }

  public static GenerationFormDTO creerGenerationFormDTO(String typeObjet,
                                                         boolean possedeFond,
                                                         boolean possedeOpacite,
                                                         boolean possedeVue,
                                                         boolean possedeSymetrie) {
    GenerationFormDTO generationFormDTO = new GenerationFormDTO();
    generationFormDTO.setModel("final");

    generationFormDTO.setTypeObjet(typeObjet);
    generationFormDTO.setMateriau("wood");
    generationFormDTO.setNomType("nom_texture");
    generationFormDTO.setCouleur("brown");
    generationFormDTO.setDescription("une description");

    if (possedeFond) {
      generationFormDTO.setFond("transparent");
    } else {
      generationFormDTO.setFond("");
    }

    if (possedeOpacite) {
      generationFormDTO.setOpacite("semi-transparent");
    } else {
      generationFormDTO.setOpacite("");
    }

    if (possedeVue) {
      generationFormDTO.setVue("top");
    } else {
      generationFormDTO.setVue("");
    }

    if (possedeSymetrie) {
      generationFormDTO.setSymetrie("radial");
    } else {
      generationFormDTO.setSymetrie("");
    }

    return generationFormDTO;
  }
}
