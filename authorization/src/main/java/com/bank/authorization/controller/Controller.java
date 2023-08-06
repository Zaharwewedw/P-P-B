package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.model.Users;
import com.bank.authorization.security.UserDetailsServerImpl;
import com.bank.authorization.service.UserService;
import com.bank.authorization.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final UserService userService;
    private final UserValidator userValidator;
    private final UserDetailsServerImpl userDetailsServer;

    @Autowired
    public Controller(UserService userService, UserValidator userValidator, UserDetailsServerImpl userDetailsServer) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.userDetailsServer = userDetailsServer;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getViu() {

        List<Users> test = userService.getAllUsers();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь успешно удален");
    }


    @PostMapping
    public ResponseEntity<?> userSave(@RequestBody @Valid UserDTO userDto,
                                      BindingResult result) {

        userValidator.validate(userDto, result);

        if (userDetailsServer.getUserPrincipalByUsername(userDto.getProfileId()).isPresent()) {
            return ResponseEntity.badRequest().body(new
                    ArrayList<>(Collections
                    .singleton("Такой пользыватель зарегестрирован")));
        }
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(err -> err instanceof FieldError ? ((FieldError) err).getField() + ":"
                            + err.getDefaultMessage() : err.getDefaultMessage()).collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        userService.register(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePage(@Valid @RequestBody UserDTO userDto,
                                        BindingResult result) {

        userValidator.validate(userDto, result);

        if (userDetailsServer.getUserPrincipalByUsername(userDto.getProfileId()).isEmpty()) {
            return ResponseEntity.badRequest().body(new
                    ArrayList<>(Collections
                    .singleton("Такой пользователь не существует")));
        }

        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(err -> err instanceof FieldError ? ((FieldError) err).getField() + ":"
                            + err.getDefaultMessage() : err.getDefaultMessage()).collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        userService.upDateUser(userDto);
        return ResponseEntity.ok("Пользователь успешно обновлен");
    }
}
