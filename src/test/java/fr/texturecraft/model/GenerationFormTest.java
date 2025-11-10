package fr.texturecraft.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fr.texturecraft.GenerationFormTestUtils.creerGenerationForm;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerationFormTest {

  @Test
  void construirePromptSelonGenerationFormSansTagsOptionnels() {
    // GIVEN
    String promptAttendu = "minecraft block : material : wood, type : nom_texture, color : brown, " +
        "features : une description, style : pixel art";

    GenerationForm generationForm = creerGenerationForm("block", false, false,
        false,false);

    // WHEN
    String promptObtenu = generationForm.construirePromptSelonGenerationForm();

    // THEN
    Assertions.assertAll(() -> assertEquals(promptAttendu, promptObtenu));
  }

  @Test
  void construirePromptSelonGenerationFormAvecTagsOptionnels() {
    // GIVEN
    String promptAttendu = "minecraft block : material : wood, type : nom_texture, color : brown, " +
        "features : une description, background : transparent, opacity : semi-transparent, view : top, symmetry : radial, style : pixel art";

    GenerationForm generationForm = creerGenerationForm("block", true, true,
        true,true);

    // WHEN
    String promptObtenu = generationForm.construirePromptSelonGenerationForm();

    // THEN
    Assertions.assertAll(() -> assertEquals(promptAttendu, promptObtenu));
  }
}