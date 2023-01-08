package com.tsubaki.converters;

import com.tsubaki.dto.UserDto;
import com.tsubaki.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto implements Convertable<User, UserDto>{
    @Override
    public UserDto transform(User src) {
        return new UserDto(
                src.getId(),
                src.getUsername(),
                src.getEmail());
    }
}
