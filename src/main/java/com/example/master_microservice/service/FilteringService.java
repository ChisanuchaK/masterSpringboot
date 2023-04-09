package com.example.master_microservice.service;

import com.example.master_microservice.models.Filtering;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilteringService {
    private static List<Filtering>  filteringList = new ArrayList<>();
        static {
            Filtering filtering = new Filtering("k" , "kl" , "k");
            filteringList.add(filtering);
        }

    public Filtering saveFiltering(String username , String university , String major){
        Filtering filtering = new Filtering(username, university, major);
        filteringList.add(filtering);
        return  filtering;
    }

    public Filtering showOne(){
        return filteringList.get(0);
    }

    public List<Filtering> filteringList(){
       return filteringList;
    }
}
