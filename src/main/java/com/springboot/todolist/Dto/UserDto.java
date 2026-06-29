package com.springboot.todolist.Dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id ;

    private String username;

    private String password;
}
