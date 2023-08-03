package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.model.Users;
import com.bank.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public  ResponseEntity<List<Users>>  getViu() {

        List<Users> test = userService.getAllUsers();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("/userSave")
    public ResponseEntity<?> userSave(@RequestBody UserDTO userDto, BindingResult result) {

        Users users;
            System.out.println(userDto.getPassword());
        try {
            users = new Users();
            users.setPassword(userDto.getPassword());
            users.setRole(userDto.getRole());
            users.setProfileId(userDto.getProfileId());
        } catch (Exception ign) {
            List<String> errors = result.getAllErrors().stream()
                    .map(err -> err instanceof FieldError ? ((FieldError) err).getField() + ":"
                            + err.getDefaultMessage() : err.getDefaultMessage()).collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        userService.getByIdUser(2L);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
