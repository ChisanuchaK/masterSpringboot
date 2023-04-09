package com.example.master_microservice.controller;

import com.example.master_microservice.service.FilteringService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @Autowired
    FilteringService filteringService;


    @GetMapping("/test")
    public MappingJacksonValue test(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteringService.filteringList());
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filtering-json" , SimpleBeanPropertyFilter.filterOutAllExcept("username" , "major")); // give in
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filtering-json" , SimpleBeanPropertyFilter.serializeAllExcept("username" , "major")); // give out
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filtering-json" , SimpleBeanPropertyFilter.serializeAll()); // give all in
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PostMapping("/saveFilter")
    public EntityModel<?> saveFiltering(@Param("username") String username , @Param("university") String university , @Param("major") String major){
        EntityModel entityModel = EntityModel.of(filteringService.saveFiltering(username , university , major));
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).saveFiltering(username , university , major));
        WebMvcLinkBuilder linkBuilder1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).test());
        entityModel.add(linkBuilder.withRel("save link"));
        entityModel.add(linkBuilder1.withRel("test"));
        filteringService.saveFiltering(username , university , major);
        return entityModel;
    }
}
