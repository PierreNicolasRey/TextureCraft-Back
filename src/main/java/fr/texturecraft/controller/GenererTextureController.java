package fr.texturecraft.controller;

import fr.texturecraft.service.GenererTextureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GenererTextureController {
  private GenererTextureService genererTextureService;

  @PostMapping
  public void genererTexture() {
    this.genererTextureService.genererTexture();
  }
}
