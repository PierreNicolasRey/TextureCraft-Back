package fr.texturecraft.mapper;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.springframework.stereotype.Component;

@Component
public class GenerationFormMapper {
  public GenerationForm mapperGenerationFormDTOToGenerationForm(GenerationFormDTO dto) {
    GenerationForm generationForm = new GenerationForm();
    generationForm.setModelEnum(ModelEnum.valueOf(dto.getModel()));

    return generationForm;
  }
}
