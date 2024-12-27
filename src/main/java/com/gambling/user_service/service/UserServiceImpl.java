package com.gambling.user_service.service;

import com.gambling.user_service.dto.UserDTO;
import com.gambling.user_service.exceptions.UserNotFoundException;
import com.gambling.user_service.mapper.UserMapper;
import com.gambling.user_service.dto.PatchUserRequest;
import com.gambling.user_service.model.User;
import com.gambling.user_service.model.UserStatus;
import com.gambling.user_service.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<UserDTO> getUsers() {
    return repository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO getUser(Long userId) {
    return repository.findById(userId)
        .map(this::convertToDTO)
        .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
  }

  @Override
  public void create(UserDTO userDTO) {
    User user = UserMapper.INSTANCE.toEntity(userDTO);
    repository.save(user);
  }

  @Override
  public void update(Long userId, PatchUserRequest request){
    User user = Optional.ofNullable(userId)
        .map(u -> userId)
        .map(repository::findByUserId)
        .orElseThrow();
    validateForPatch(request);
    updateUser(user, request);
    repository.save(user);
  }

  private void validateForPatch(PatchUserRequest request){
    if(request.getFirstName() != null && request.getFirstName().isBlank()){
      throw new IllegalArgumentException("firstName can not be blank");
    }
    if(request.getLastName() != null && request.getLastName().isBlank()){
      throw new IllegalArgumentException("lastName can not be blank");
    }
    if(request.getEmail() != null && request.getEmail().isBlank()){
      throw new IllegalArgumentException("Email can not be blank");
    }
    if(request.getIban() != null && request.getIban().isBlank()){
      throw new IllegalArgumentException("IBAN can not be blank");
    }
  }

  private void updateUser(User user, PatchUserRequest request){
    if(request.getFirstName() != null) {
      user.setFirstName(request.getFirstName());
    }
    if(request.getLastName() != null) {
      user.setLastName(request.getLastName());
    }
    if(request.getEmail() != null) {
      user.setEmail(request.getEmail());
    }
    if(request.getIban() != null) {
      user.setIban(request.getIban());
    }
  }

  public BigDecimal getUserBalance(Long userId){
    return Optional.ofNullable(userId)
        .map(u -> userId)
        .map(repository::findByUserId)
        .map(User::getBalance)
        .orElseThrow(()-> new UserNotFoundException("User not found with id: " + userId));
  }

  @Override
  public void increaseBalance(Long userId, BigDecimal amount){
    User user = repository.findByUserId(userId);
      user.setBalance(user.getBalance().add(amount));
      repository.save(user);
  }

  @Override
  public void decreaseBalance(Long userId, BigDecimal amount){
    User user = repository.findByUserId(userId);

    BigDecimal newBalance = user.getBalance().subtract(amount);

    if(newBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Insufficient balance!");
    }
    user.setBalance(newBalance);
    repository.save(user);
  }

  public void setUserInactive(Long userId){
    User user = Optional.ofNullable(userId)
        .map(u -> userId)
        .map(repository::findByUserId)
        .orElseThrow();
    user.setStatus(UserStatus.INACTIVE);
    repository.save(user);
  }

  public void setUserActive(Long userId){
    User user = Optional.ofNullable(userId)
        .map(u -> userId)
        .map(repository::findByUserId)
        .orElseThrow();
    user.setStatus(UserStatus.ACTIVE);
    repository.save(user);
  }

  private UserDTO convertToDTO(User user) {
    return UserMapper.INSTANCE.toDTO(user);
  }
}
