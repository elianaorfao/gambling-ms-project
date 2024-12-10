package com.gambling.user_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String iban;
}
