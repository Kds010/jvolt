package com.ho.jvolt.user.mapper;

import com.ho.jvolt.auth.dto.RegisterDto;
import com.ho.jvolt.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterDto.Response userToRegisterResponse(User user, boolean success, String message);

    @AfterMapping
    default void addCustomFields(@MappingTarget RegisterDto.Response registerResponse) {
        registerResponse.setSuccess(true); // 기본 값 설정
        registerResponse.setMessage("User fetched successfully");
    }
}
