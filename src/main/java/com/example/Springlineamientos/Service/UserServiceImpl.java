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
        if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()){
            throw new Exception("Confirm Password es obligatorio");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new Exception("Password y Confirm Password no coinciden");
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

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("El usuario a editar no existe"));
    }

    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return userRepository.save(toUser);
    }

    //Metodo para proteccion de datos generales
    protected void mapUser(User from, User to){
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());

    }
}
