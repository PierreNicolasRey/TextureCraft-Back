package fr.texturecraft.enums;

public enum ModelEnum {
  FINAL,
  ETAPE_220,
  ETAPE_440,
  ETAPE_660,
  ETAPE_880;


  public static String getName(ModelEnum modelEnum) {
    return modelEnum.name().toLowerCase();
  }
}
