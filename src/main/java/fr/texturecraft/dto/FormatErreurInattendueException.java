package fr.texturecraft.dto;

public class FormatErreurInattendueException extends RuntimeException {
  public FormatErreurInattendueException(String message) {
    super("Format de l'erreur réceptionnée inconnu : " + message);
  }
}
