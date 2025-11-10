package fr.texturecraft.mapper;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.springframework.stereotype.Component;

@Component
public class GenerationFormMapper {
  public GenerationForm mapperGenerationFormDTOToGenerationForm(GenerationFormDTO dto) {
    GenerationForm generationForm = new GenerationForm();
    generationForm.setModelEnum(ModelEnum.valueOf(dto.model().toUpperCase()));

    mapperTagsObligatoiresDTOToModel(dto, generationForm);
    mapperTagsOptionnelsDTOToModel(dto, generationForm);

    return generationForm;
  }

  private void mapperTagsObligatoiresDTOToModel(GenerationFormDTO dto, GenerationForm model) {
    model.setTypeObjet(dto.typeObjet());
    model.setMateriau(dto.materiau());
    model.setNomType(dto.nomType());
    model.setCouleur(dto.couleur());
    model.setDescription(dto.description());
  }

  private void mapperTagsOptionnelsDTOToModel(GenerationFormDTO dto, GenerationForm model) {
      model.setFond(dto.fond());
      model.setOpacite(dto.opacite());
      model.setVue(dto.vue());
      model.setSymetrie(dto.symetrie());
  }
}
