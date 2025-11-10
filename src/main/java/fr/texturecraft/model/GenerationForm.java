package fr.texturecraft.model;

import fr.texturecraft.enums.ModelEnum;

public class GenerationForm {
  private ModelEnum modelEnum;
  private String resolution;

  // Tags obligatoires
  private String typeObjet;
  private String materiau;
  private String nomType;
  private String couleur;
  private String description;

  // Tags optionnels
  private String fond;
  private String opacite;
  private String vue;
  private String symetrie;

  public ModelEnum getModelEnum() {
    return modelEnum;
  }

  public void setModelEnum(ModelEnum modelEnum) {
    this.modelEnum = modelEnum;
  }

  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public String getTypeObjet() {
    return typeObjet;
  }

  public void setTypeObjet(String typeObjet) {
    this.typeObjet = typeObjet;
  }

  public String getMateriau() {
    return materiau;
  }

  public void setMateriau(String materiau) {
    this.materiau = materiau;
  }

  public String getNomType() {
    return nomType;
  }

  public void setNomType(String nomType) {
    this.nomType = nomType;
  }

  public String getCouleur() {
    return couleur;
  }

  public void setCouleur(String couleur) {
    this.couleur = couleur;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFond() {
    return fond;
  }

  public void setFond(String fond) {
    this.fond = fond;
  }

  public String getOpacite() {
    return opacite;
  }

  public void setOpacite(String opacite) {
    this.opacite = opacite;
  }

  public String getVue() {
    return vue;
  }

  public void setVue(String vue) {
    this.vue = vue;
  }

  public String getSymetrie() {
    return symetrie;
  }

  public void setSymetrie(String symetrie) {
    this.symetrie = symetrie;
  }
}
