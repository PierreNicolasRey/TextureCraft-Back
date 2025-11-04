package fr.texturecraft.exception;

public class FormatErreurInattendueException extends RuntimeException {
  public FormatErreurInattendueException(String message) {
    super("Format de l'erreur réceptionnée inconnu : " + message);
  }
}
