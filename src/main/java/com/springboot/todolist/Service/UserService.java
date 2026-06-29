package com.springboot.todolist.Service;

import com.springboot.todolist.Dto.UserDto;


public interface UserService {

    UserDto updateUser(Long id, UserDto userDto);

    UserDto register(UserDto userDto);

    public String verify(UserDto userDto);
}
