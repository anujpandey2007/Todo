package com.springboot.todolist.Service.impl;


import com.springboot.todolist.Dto.UserDto;
import com.springboot.todolist.Entity.Users;
import com.springboot.todolist.Reposiytory.UserRepository;
import com.springboot.todolist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private  final  UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();


    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        Users user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));

        Users updatedUser = repo.save(user);

        return mapToDto(updatedUser);

    }

    @Override
    public UserDto register(UserDto userDto) {
        Users user = mapToEntity(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        Users updatedUser = repo.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public String verify(UserDto userDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userDto.getUsername());
        }

        return "failure";
    }

    // Helper Method: Convert DTO to Entity

    private Users mapToEntity(UserDto dto) {
        Users entity = new Users();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    // Helper Method: Convert Entity to DTO
    private UserDto mapToDto(Users entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
