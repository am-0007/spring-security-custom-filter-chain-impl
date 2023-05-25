package com.springsecuirty2023;

import com.springsecuirty2023.config.PasswordConfig;
import com.springsecuirty2023.entities.User;
import com.springsecuirty2023.entities.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);

    default User toUser(UserDto userDto) {
        return new User(
                generateUserId(),
                userDto.getUsername(),
                userDto.getRole()
        );
    }

    default UserDto toUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getRole()
        );
    }

    default String generateUserId() {
        return "ss023-" + UUID.randomUUID().toString();
    }
}
