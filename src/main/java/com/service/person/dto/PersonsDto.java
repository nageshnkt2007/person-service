package com.service.person.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonsDto {
    List<PersonDto> person;
    int totalPages;
}
