package fr.texturecraft.mapper;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.texturecraft.GenerationFormTestUtils.creerGenerationFormDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GenerationFormMapperTest {
  private final GenerationFormMapper generationFormMapper = new GenerationFormMapper();

  @Test
  void mapperGenerationFormDTOToGenerationFormSansTagsOptionnels() {
    // GIVEN
    GenerationFormDTO dto = creerGenerationFormDTO("block", false,
        false, false, false);

    // WHEN
    GenerationForm model =
        generationFormMapper.mapperGenerationFormDTOToGenerationForm(dto);

    // THEN
    Assertions.assertAll(() -> {
      assertEquals(ModelEnum.FINAL, model.getModelEnum());
      // Tags obligatoires
      assertEquals(dto.typeObjet(), model.getTypeObjet());
      assertEquals(dto.materiau(), model.getMateriau());
      assertEquals(dto.nomType(), model.getNomType());
      assertEquals(dto.couleur(), model.getCouleur());
      assertEquals(dto.description(), model.getDescription());

      // Tags optionnels vides
      assertEquals("", model.getFond());
      assertEquals("", model.getOpacite());
      assertEquals("", model.getVue());
      assertEquals("", model.getSymetrie());
    });
  }

  @Test
  void mapperGenerationFormDTOToGenerationFormAvecTagsOptionnels() {
    // GIVEN
    GenerationFormDTO dto = creerGenerationFormDTO("block", true,
        true, true, true);

    // WHEN
    GenerationForm model =
        generationFormMapper.mapperGenerationFormDTOToGenerationForm(dto);

    // THEN
    Assertions.assertAll(() -> {
      assertEquals(ModelEnum.FINAL, model.getModelEnum());
      // Tags obligatoires
      assertEquals(dto.typeObjet(), model.getTypeObjet());
      assertEquals(dto.materiau(), model.getMateriau());
      assertEquals(dto.nomType(), model.getNomType());
      assertEquals(dto.couleur(), model.getCouleur());
      assertEquals(dto.description(), model.getDescription());

      // Tags optionnels vides
      assertEquals(dto.fond(), model.getFond());
      assertEquals(dto.opacite(), model.getOpacite());
      assertEquals(dto.vue(), model.getVue());
      assertEquals(dto.symetrie(), model.getSymetrie());
    });
  }

  @Test
  void verifierPropagationExceptionQuandMappingEchoueTest() {
    // GIVEN
    GenerationFormDTO generationFormDTO = new GenerationFormDTO(
        "model_inexistant",
        "16x16",
        "block",
        "wood",
        "nom_texture",
        "brown",
        "une description",
        "",
        "",
        "",
        ""
    );

    // THEN
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      // WHEN
      generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO);
    });
  }
}