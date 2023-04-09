package com.example.master_microservice.models;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("filtering-json")
//@JsonIgnoreProperties({"major"})
public class Filtering {
//    @JsonProperty("un")
    private String username;
    private String university;
    private String major;
}
