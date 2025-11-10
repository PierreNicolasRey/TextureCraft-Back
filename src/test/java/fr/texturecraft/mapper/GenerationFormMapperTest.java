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
      assertEquals(ModelEnum.FINAL, model.modelEnum());
      assertEquals(dto.resolution(), model.resolution());
      // Tags obligatoires
      assertEquals(dto.typeObjet(), model.typeObjet());
      assertEquals(dto.materiau(), model.materiau());
      assertEquals(dto.nomType(), model.nomType());
      assertEquals(dto.couleur(), model.couleur());
      assertEquals(dto.description(), model.description());

      // Tags optionnels vides
      assertEquals("", model.fond());
      assertEquals("", model.opacite());
      assertEquals("", model.vue());
      assertEquals("", model.symetrie());
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
      assertEquals(ModelEnum.FINAL, model.modelEnum());
      assertEquals(dto.resolution(), model.resolution());
      // Tags obligatoires
      assertEquals(dto.typeObjet(), model.typeObjet());
      assertEquals(dto.materiau(), model.materiau());
      assertEquals(dto.nomType(), model.nomType());
      assertEquals(dto.couleur(), model.couleur());
      assertEquals(dto.description(), model.description());

      // Tags optionnels vides
      assertEquals(dto.fond(), model.fond());
      assertEquals(dto.opacite(), model.opacite());
      assertEquals(dto.vue(), model.vue());
      assertEquals(dto.symetrie(), model.symetrie());
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