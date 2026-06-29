package com.springboot.todolist.Controller;

import com.springboot.todolist.Dto.UserDto;
import com.springboot.todolist.Entity.UserPrincipal;
import com.springboot.todolist.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {


    private final UserService service;


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        UserDto userRegistered = service.register(userDto);
        return new ResponseEntity<>(userRegistered, HttpStatus.CREATED) ;
    }

    @GetMapping("/register/me")
    public ResponseEntity<UserDto> getMe(@AuthenticationPrincipal UserPrincipal principal){
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDto userDto = new UserDto();
        userDto.setId(principal.getId());
        userDto.setUsername(principal.getUsername());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/register/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto userUpdated = service.updateUser(id,userDto);
        return new ResponseEntity<>(userUpdated,HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){

        return service.verify(userDto);
    }


}
