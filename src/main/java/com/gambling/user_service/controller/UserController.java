package com.gambling.user_service.controller;

import com.gambling.user_service.dto.UserDTO;
import com.gambling.user_service.dto.PatchUserRequest;
import com.gambling.user_service.service.UserService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/users")
  public List<UserDTO> listUsers(){
    return service.getUsers();
  }

  @GetMapping("/{id}")
  public UserDTO getUser(@PathVariable("id") Long userId) {
    return Optional.ofNullable(userId)
        .map(u -> userId)
        .map(service::getUser)
        .orElseThrow();
  }

  @PatchMapping("/{id}")
  public void update(@PathVariable("id") Long userId, @Valid @RequestBody PatchUserRequest request) {
    service.update(userId, request);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody UserDTO user) {
    service.create(user);
  }

  @GetMapping("/{id}/balance")
  public BigDecimal getBalance(@PathVariable("id") Long userId){
    return service.getUserBalance(userId);
  }

  @PatchMapping("/{id}/balance/increase")
  @ResponseStatus(HttpStatus.OK)
  public void increaseBalance(@PathVariable("id") Long userId, @Valid @RequestBody BigDecimal amount){
    service.increaseBalance(userId, amount);
  }

  @PatchMapping("/{id}/balance/decrease")
  @ResponseStatus(HttpStatus.OK)
  public void decreaseBalance(@PathVariable("id") Long userId,@Valid @RequestBody BigDecimal amount){
    service.decreaseBalance(userId, amount);
  }

  @PatchMapping("/{id}/disable")
  public void disableUser(@PathVariable("id") Long userId) {
    service.setUserInactive(userId);
  }

  @PatchMapping("/{id}/enable")
  public void enableUser(@PathVariable("id") Long userId) {
    service.setUserActive(userId);
  }

}

