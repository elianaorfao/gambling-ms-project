package com.gambling.user_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDTO(
    Long userId,
    String firstName,
    String lastName,
    String email,
    String iban,
    BigDecimal balance,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate birthday,
    LocalDateTime entryDate,
    LocalDateTime lastUpdateDate
) {
}
