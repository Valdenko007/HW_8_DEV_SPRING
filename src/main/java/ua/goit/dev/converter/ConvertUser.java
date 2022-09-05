package ua.goit.dev.converter;

import ua.goit.dev.model.dao.User;
import ua.goit.dev.model.dto.UserDto;

public class ConvertUser {
    public UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword()).build();
    }
}
