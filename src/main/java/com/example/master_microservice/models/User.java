package com.example.master_microservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private LocalDate brithDay;
}
