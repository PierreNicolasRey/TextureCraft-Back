package fr.texturecraft.controller;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.dto.GenerationResponseDTO;
import fr.texturecraft.mapper.GenerationFormMapper;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class GenererTextureController {
  private final GenererTextureService genererTextureService;
  private final GenerationFormMapper generationFormMapper;

  public GenererTextureController(GenererTextureService genererTextureService,
                                  GenerationFormMapper generationFormMapper) {
    this.genererTextureService = genererTextureService;
    this.generationFormMapper = generationFormMapper;
  }

  @PostMapping("/generer-texture")
  public ResponseEntity<GenerationResponseDTO> genererTexture(@RequestBody GenerationFormDTO generationFormDTO) {
    GenerationForm generationForm = generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO);

    GenerationResponseDTO generationResponseDTO = this.genererTextureService.genererTexture(generationForm);

    return new ResponseEntity<>(generationResponseDTO, HttpStatus.OK);
  }
}
