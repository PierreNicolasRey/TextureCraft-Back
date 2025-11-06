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
      assertEquals(dto.getTypeObjet(), model.getTypeObjet());
      assertEquals(dto.getMateriau(), model.getMateriau());
      assertEquals(dto.getNomType(), model.getNomType());
      assertEquals(dto.getCouleur(), model.getCouleur());
      assertEquals(dto.getDescription(), model.getDescription());

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
      assertEquals(dto.getTypeObjet(), model.getTypeObjet());
      assertEquals(dto.getMateriau(), model.getMateriau());
      assertEquals(dto.getNomType(), model.getNomType());
      assertEquals(dto.getCouleur(), model.getCouleur());
      assertEquals(dto.getDescription(), model.getDescription());

      // Tags optionnels vides
      assertEquals(dto.getFond(), model.getFond());
      assertEquals(dto.getOpacite(), model.getOpacite());
      assertEquals(dto.getVue(), model.getVue());
      assertEquals(dto.getSymetrie(), model.getSymetrie());
    });
  }

  @Test
  void verifierPropagationExceptionQuandMappingEchoueTest() {
    // GIVEN
    GenerationFormDTO generationFormDTO = creerGenerationFormDTO("block", false,
        false, false, false);
    generationFormDTO.setModel("model_inexistant");

    // THEN
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      // WHEN
      generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO);
    });
  }
}