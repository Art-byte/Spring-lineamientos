package com.example.Springlineamientos.Service;

import com.example.Springlineamientos.Entity.User;
import org.springframework.stereotype.Service;

/*
* Esta interface debe crearse para posteriormente se implementada
* para el manejo y tambien por buena practica de programacion.
* */
public interface UserService {
    //Iterable quiere decir que es cualquier coleccion
    public Iterable<User> getAllUsers();
    public User createUser(User user) throws Exception;
    public User getUserById(Long id)throws Exception;
    public User updateUser(User user)throws Exception;
}
