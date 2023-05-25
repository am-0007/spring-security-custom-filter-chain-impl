package com.springsecuirty2023;

import com.springsecuirty2023.config.PasswordConfig;
import com.springsecuirty2023.entities.User;
import com.springsecuirty2023.entities.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {

    static final UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);

    default User toUser(UserDto userDto) {
        return User.builder()
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(PasswordConfig.passwordEncoder().encode(userDto.getPassword()))
                .build();
    }

    default UserDto toUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
