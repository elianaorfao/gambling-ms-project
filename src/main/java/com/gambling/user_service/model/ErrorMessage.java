package com.gambling.user_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
  private int code;
  private String message;

}
