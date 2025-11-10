package fr.texturecraft.mapper;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.enums.ModelEnum;
import fr.texturecraft.model.GenerationForm;
import org.springframework.stereotype.Component;

@Component
public class GenerationFormMapper {
  public GenerationForm mapperGenerationFormDTOToGenerationForm(GenerationFormDTO dto) {
    return new GenerationForm(
        ModelEnum.valueOf(dto.model().toUpperCase()),
        dto.resolution(),
        dto.typeObjet(),
        dto.materiau(),
        dto.nomType(),
        dto.couleur(),
        dto.description(),
        dto.fond(),
        dto.opacite(),
        dto.vue(),
        dto.symetrie()
    );
  }
}
