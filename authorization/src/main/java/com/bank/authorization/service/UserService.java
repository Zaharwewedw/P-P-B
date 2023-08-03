package com.bank.authorization.service;

import com.bank.authorization.model.Users;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();

    void upDateUser(Users user);

    void deleteUser(Long id);

    Users getByIdUser(Long id);
    void AddUserStarter();
}
