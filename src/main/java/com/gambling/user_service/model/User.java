package com.gambling.user_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "T_USER")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "FIRST_NAME", length = 30, nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", length = 30, nullable = false)
  private String lastName;

  @Column(name = "EMAIL", length = 30, nullable = false)
  private String email;

  @Column(name = "IBAN", length = 30, nullable = false)
  private String iban;

  @Column(name = "BALANCE", length = 30)
  private BigDecimal balance;

  @Column(name = "STATUS", length = 30)
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @Column(name = "BIRTHDAY",  nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate birthday;

  @JsonIgnore
  @CreationTimestamp
  @Column(name = "ENTRY_DATE")
  private LocalDateTime entryDate;

  @JsonIgnore
  @UpdateTimestamp
  @Column(name = "LAST_UPDATE_DATE")
  private LocalDateTime lastUpdateDate;


}
