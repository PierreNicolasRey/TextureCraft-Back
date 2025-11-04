package fr.texturecraft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
  private String status;
  private String message;
  private int code;
}
