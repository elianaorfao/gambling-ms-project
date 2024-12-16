package com.gambling.user_service.controller;

import com.gambling.user_service.dto.UserDTO;
import com.gambling.user_service.dto.PatchUserRequest;
import com.gambling.user_service.service.UserService;
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
  public UserDTO getUser(@PathVariable("id") String userId) {
    return Optional.ofNullable(userId)
        .map(u -> Long.valueOf(userId))
        .map(service::getUser)
        .orElseThrow();
  }

  @PatchMapping("/{id}")
  public void update(@PathVariable("id") String userId, @RequestBody PatchUserRequest request) {
    service.update(Long.valueOf(userId), request);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody UserDTO user) {
    service.create(user);
  }

  @GetMapping("/{id}/balance")
  public BigDecimal getBalance(@PathVariable("id") String userId){
    return service.getUserBalance(userId);
  }

  @PatchMapping("/{id}/balance/increase")
  @ResponseStatus(HttpStatus.OK)
  public void increaseBalance(@PathVariable("id") String userId, @RequestBody BigDecimal amount){
    service.increaseBalance(Long.valueOf(userId), amount);
  }

  @PatchMapping("/{id}/balance/decrease")
  @ResponseStatus(HttpStatus.OK)
  public void decreaseBalance(@PathVariable("id") String userId,@RequestBody BigDecimal amount){
    service.decreaseBalance(Long.valueOf(userId), amount);
  }

}

