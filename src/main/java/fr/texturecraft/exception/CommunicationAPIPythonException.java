package fr.texturecraft.exception;

public class CommunicationAPIPythonException extends RuntimeException {
  public CommunicationAPIPythonException(String message) {
    super("Erreur de communication avec l'API Python : " + message);
  }
}
