package fr.texturecraft.exception;

public class ValidationRequeteAPIPythonException extends RuntimeException {
  public ValidationRequeteAPIPythonException(String message) {
    super("Erreur de validation de la requête API Python : " + message);
  }
}
