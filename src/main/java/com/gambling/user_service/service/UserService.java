package com.gambling.user_service.service;

import com.gambling.user_service.dto.UserDTO;
import com.gambling.user_service.dto.PatchUserRequest;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

  public List<UserDTO> getUsers();
  public UserDTO getUser(Long userId);
  public void create(UserDTO userDTO);
  public void update(Long userId, PatchUserRequest request);
  public BigDecimal getUserBalance(String userId);
  public void increaseBalance(Long userId, BigDecimal amount);
  public void decreaseBalance(Long userId, BigDecimal amount);
}
