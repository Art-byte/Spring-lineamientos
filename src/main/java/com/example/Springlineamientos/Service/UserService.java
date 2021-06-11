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
}
