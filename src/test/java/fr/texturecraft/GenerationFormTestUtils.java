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
    return new GenerationForm(
        ModelEnum.FINAL,
        "16x16",
        typeObjet,
        "wood",
        "nom_texture",
        "brown",
        "une description",
        possedeFond ? "transparent" : "",
        possedeOpacite ? "semi-transparent" : "",
        possedeVue ? "top" : "",
        possedeSymetrie ? "radial" : ""
    );
  }

  public static GenerationFormDTO creerGenerationFormDTO(String typeObjet,
                                                         boolean possedeFond,
                                                         boolean possedeOpacite,
                                                         boolean possedeVue,
                                                         boolean possedeSymetrie) {
    return new GenerationFormDTO(
        "final",
        "16x16",
        typeObjet,
        "wood",
        "nom_texture",
        "brown",
        "une description",
        possedeFond ? "transparent" : "",
        possedeOpacite ? "semi-transparent": "",
        possedeVue ? "top" : "",
        possedeSymetrie ? "radial" : "");
  }
}
