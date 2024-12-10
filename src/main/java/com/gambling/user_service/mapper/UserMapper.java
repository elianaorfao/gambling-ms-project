package com.gambling.user_service.mapper;

import com.gambling.user_service.dto.UserDTO;
import com.gambling.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDTO toDTO(User user);

  User toEntity(UserDTO userDTO);
}
