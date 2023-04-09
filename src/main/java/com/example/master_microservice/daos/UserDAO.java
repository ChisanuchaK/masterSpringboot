package com.example.master_microservice.daos;

import com.example.master_microservice.models.Customer;
import com.example.master_microservice.models.Name;
import com.example.master_microservice.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAO {


    private int id = 1;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Kim", LocalDate.of(2002, 1, 25)));
        users.add(new User(2, "Linly", LocalDate.of(2010, 2, 1)));
        users.add(new User(3, "Kang", LocalDate.of(1999, 9, 22)));
    }

    private static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(new Name("Chisanuca", "Somboonwanna"), 2));
        customers.add(new Customer(new Name("Chisanuca", "Somboonwanna"), 3));
        customers.add(new Customer(new Name("Chisanuca", "Somboonwanna"), 4));
    }

    public List<User> findAllUser() {
        return users;
    }

    public List<Customer> findCustomer() {
        return customers;
    }

    public User findOneUser(int id) {
        Predicate<? super User> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().get();
    }

    public ResponseEntity<User> saveUser(User user) {
        user.setId(id);
        users.add(user);
        id++;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public void deleteUser(int id) {
        Predicate<User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }
}
