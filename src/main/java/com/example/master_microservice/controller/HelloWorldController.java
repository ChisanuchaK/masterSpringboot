package com.example.master_microservice.controller;

import com.example.master_microservice.daos.UserDAO;
import com.example.master_microservice.models.Customer;
import com.example.master_microservice.models.HelloWorldBean;
import com.example.master_microservice.models.User;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@RestController
public class HelloWorldController {


    @Autowired
    UserDAO userDAO;


    @GetMapping("/hello-world")
    public String Hello() {
        return "Hello world !!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloBean() {
        return new HelloWorldBean("Hello world Bean");
    }

    @GetMapping("/customers")
    public List<Customer> findCustomer() {
        return userDAO.findCustomer();
    }

    @GetMapping("/hello-world-parameter/{name}")
    public HelloWorldBean HelloBeanParameter(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world %s", name));
    }

    @GetMapping("/all-user")
    public List<User> findALlUser() {
        return userDAO.findAllUser();
    }

    @GetMapping("/user/{id}")
    public User findOneUser(@PathVariable int id) {
        return userDAO.findOneUser(id);
    }


    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userDAO.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void>  deleteUser(@PathVariable int id) {
        userDAO.deleteUser(id);
        return ResponseEntity.status(200).build();
    }

    //hateoas
    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping(path = "user/hateoas/{id}")
    public EntityModel<User> userEntityModelPerson1(@PathVariable int id) {
        EntityModel entityModel = EntityModel.of( userDAO.findOneUser(id));
        WebMvcLinkBuilder linkFindAllUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findALlUser());
        WebMvcLinkBuilder linkHelloBean = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).HelloBean());
        WebMvcLinkBuilder linkDeleteMyUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUser(id));

        entityModel.add( linkFindAllUser.withRel("all-user").withTitle("title all users") , linkHelloBean.withRel("hello-bean") , linkDeleteMyUser.withRel("delete-user").withTitle("delete user") );
        return entityModel;
    }
}
