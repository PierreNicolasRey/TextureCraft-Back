package fr.texturecraft.exception;

import fr.texturecraft.dto.ErrorDetails;

public class ErreurInterneAPIPythonException extends RuntimeException {
  public ErreurInterneAPIPythonException(ErrorDetails errorDetails) {
    super("Erreur interne de l'API Python : \n"
        + "Code : " + errorDetails.getCode() + " \n"
        + "Message : " + errorDetails.getMessage()
    );
  }
}
