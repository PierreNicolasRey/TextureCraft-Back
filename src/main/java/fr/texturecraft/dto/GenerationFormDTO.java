package fr.texturecraft.dto;

public class GenerationFormDTO {
  private String model;

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

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
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
