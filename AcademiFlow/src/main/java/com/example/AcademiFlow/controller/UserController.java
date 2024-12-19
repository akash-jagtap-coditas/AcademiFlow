package com.example.AcademiFlow.controller;

import com.example.AcademiFlow.dto.ApiResponse;
import com.example.AcademiFlow.dto.PasswordDto;
import com.example.AcademiFlow.dto.UserDto;
import com.example.AcademiFlow.repository.UsersRepository;
import com.example.AcademiFlow.service.Impl.JwtServiceImpl;
import com.example.AcademiFlow.service.Impl.UserServiceImpl;
import com.example.AcademiFlow.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("academiflow/users")
public class UserController {

    JwtServiceImpl jwtService;
    UserServiceImpl userService;

    @Autowired
    public UserController(JwtServiceImpl jwtService,UserServiceImpl userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto){
        UserDto response = userService.createUser(userDto);
        return ResponseUtil.created(response,"User created successfully.");
    }

    @PostMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<Object>> uploadProfilePicture(@PathVariable Long userId, @RequestParam MultipartFile profilePicture) throws IOException {
        userService.uploadProfilePicture(profilePicture,userId);
        return ResponseUtil.created("Profile image uploaded successfully.");
    }


    @PostMapping("/setpassword")
    public ResponseEntity<ApiResponse<Object>> setPassword(@RequestBody PasswordDto passwordDto,@RequestParam String key){
        userService.setPassword(key,passwordDto.getPassword());
       return ResponseUtil.success("Password set successfully.");
    }

    @GetMapping("/{pageNo}")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(@PathVariable Long pageNo,@RequestParam(required = false) String sort,@RequestParam(required = false) String filter){
        List<UserDto> userDtoList = userService.getAlUsers(pageNo,sort,filter);
        return ResponseUtil.success(userDtoList,"All users retrived successfully");
    }
}
