package com.example.master_microservice.controller;

import com.example.master_microservice.daos.UserDAO;
import com.example.master_microservice.models.HelloWorldBean;
import com.example.master_microservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/hello-world")
    public String Hello(){
        return "Hello world !!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloBean(){
        return new HelloWorldBean("Hello world Bean");
    }

    @GetMapping("/hello-world-parameter/{name}")
    public HelloWorldBean HelloBeanParameter(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world %s" , name));
    }

    @GetMapping("/all-user")
    public List<User> findALlUser(){
        return userDAO.findAllUser();
    }

    @GetMapping("/user/{id}")
    public User findOneUser(@PathVariable int id){
      return  userDAO.findOneUser(id);
    }

    @PostMapping("/save-user")
    public String saveUser(@Param("id") int id , @Param("name") String name , @Param("birthDay")LocalDate birthDay){
        userDAO.saveUser(id , name , birthDay);
        return "save user success" + name;
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable int id){
        userDAO.deleteUser(id);
    }


}
