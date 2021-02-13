package com.service.person.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PersonDto {

    private String id;

    @NotBlank(message="first_name cannot be blank")
    private String first_name;

    @NotBlank(message="last_name cannot be blank")
    private String last_name;

    @NotBlank(message="age cannot be blank")
    private String age;

    @NotBlank(message="favourite_colour cannot be blank")
    private String favourite_colour;
}
