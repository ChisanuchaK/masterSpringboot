package com.example.master_microservice.daos;

import com.example.master_microservice.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAO {

    private static List<User> users = new ArrayList<>();
        static {
            users.add( new User(1 , "Kim" , LocalDate.of(2002 , 1 , 25)));
            users.add( new User(2 , "Linly" , LocalDate.of(2010 , 2 , 1)));
            users.add( new User(3 , "Kang" , LocalDate.of(1999 , 9 , 22)));
        }

        public  List<User> findAllUser(){
            return users;
        }

        public User findOneUser(int id){
            Predicate<? super User> predicate = user -> user.getId() == id;
            return users.stream().filter(predicate).findFirst().get();
        }

        public String saveUser(int id , String name , LocalDate birthDay){
             users.add(new User(id , name , birthDay));
            return "save user success :" +  name;
        }

        public void deleteUser(int id){
            Predicate<?super User> predicate = user -> user.getId() == id;
            users.removeIf(predicate);
        }
}
