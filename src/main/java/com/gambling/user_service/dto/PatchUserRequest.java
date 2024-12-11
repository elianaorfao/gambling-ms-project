package com.gambling.user_service.dto;

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
