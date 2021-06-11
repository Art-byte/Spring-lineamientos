package com.example.Springlineamientos.Service;

import com.example.Springlineamientos.Entity.User;
import com.example.Springlineamientos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    private boolean checkUsernameAvailable(User username) throws Exception{
        Optional<User> userFound = userRepository.findByUsername(username.getUsername());
        if(userFound.isPresent()){
            throw new Exception("Nombre de usuario no disponible");
        }
        return true;
    }

    private boolean checkPasswordValid(User user)throws Exception{
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new Exception("El password no coincide");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws Exception {
        if(checkUsernameAvailable(user) && checkPasswordValid(user)){
           user = userRepository.save(user);
        }
        return user;
    }
}
