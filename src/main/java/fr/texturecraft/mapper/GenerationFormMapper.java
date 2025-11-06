package fr.texturecraft.mapper;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.springframework.stereotype.Component;

@Component
public class GenerationFormMapper {
  public GenerationForm mapperGenerationFormDTOToGenerationForm(GenerationFormDTO dto) {
    GenerationForm generationForm = new GenerationForm();
    generationForm.setModelEnum(ModelEnum.valueOf(dto.getModel().toUpperCase()));

    mapperTagsObligatoiresDTOToModel(dto, generationForm);
    mapperTagsOptionnelsDTOToModel(dto, generationForm);

    return generationForm;
  }

  private void mapperTagsObligatoiresDTOToModel(GenerationFormDTO dto, GenerationForm model) {
    model.setTypeObjet(dto.getTypeObjet());
    model.setMateriau(dto.getMateriau());
    model.setNomType(dto.getNomType());
    model.setCouleur(dto.getCouleur());
    model.setDescription(dto.getDescription());
  }

  private void mapperTagsOptionnelsDTOToModel(GenerationFormDTO dto, GenerationForm model) {
      model.setFond(dto.getFond());
      model.setOpacite(dto.getOpacite());
      model.setVue(dto.getVue());
      model.setSymetrie(dto.getSymetrie());
  }
}
