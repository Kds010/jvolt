package com.ho.jvolt.user.mapper;

import com.ho.jvolt.common.security.auth.response.RegisterResponse;
import com.ho.jvolt.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "success", ignore = true) // Custom fields
    @Mapping(target = "message", ignore = true) // Custom fields
    RegisterResponse userToRegisterResponse(User user);

    @AfterMapping
    default void addCustomFields(@MappingTarget RegisterResponse registerResponse) {
        registerResponse.setSuccess(true); // 기본 값 설정
        registerResponse.setMessage("User fetched successfully");
    }
}
