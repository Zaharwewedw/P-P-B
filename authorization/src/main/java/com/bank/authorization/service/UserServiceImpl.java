package com.bank.authorization.service;

import com.bank.authorization.model.Users;
import com.bank.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    @PostConstruct
    public void AddUserStarter () {
        Users user = new Users();
        user.setPassword("Kata");
        user.setProfileId(12131);
        user.setRole("ROLE_ADMIN");
        userRepository.saveNoJPA(user.getPassword(), user.getRole(), user.getProfileId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void upDateUser(Users user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Users getByIdUser(Long id) {
        return userRepository.findById(id).get();
    }
}
