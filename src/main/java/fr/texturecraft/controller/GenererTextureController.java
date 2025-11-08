package fr.texturecraft.controller;

import fr.texturecraft.dto.GenerationFormDTO;
import fr.texturecraft.mapper.GenerationFormMapper;
import fr.texturecraft.model.GenerationForm;
import fr.texturecraft.service.GenererTextureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/generer")
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
  public ResponseEntity<byte[]> genererTexture(@RequestBody GenerationFormDTO generationFormDTO) {
    GenerationForm generationForm = generationFormMapper.mapperGenerationFormDTOToGenerationForm(generationFormDTO);

    byte[] imageBytes = this.genererTextureService.genererTexture(generationForm);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    headers.setContentLength(imageBytes.length);

    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
  }
}
