package com.service.person.services;

import com.service.person.dto.PersonDto;
import com.service.person.dto.PersonsDto;
import com.service.person.exception.BadRequestException;

import java.util.List;

public interface IPerson {
    /**
     * @param person .
     * @return PersonDto .
     */
    PersonDto createPerson(PersonDto person);

    /**
     * @param id .
     * @return PersonDto .
     */
    PersonDto getById(String id);

    /**
     * @return All Person Objects .
     * @param page
     */
    PersonsDto getAllPersons(Integer page);

    /**
     * @param personDtoList .
     * @return List of Persons saved .
     */
    List<PersonDto> createPersons(List<PersonDto> personDtoList);

    /**
     * @param personDto .
     * @return updated PersonDto object .
     */
    PersonDto updatePerson(PersonDto personDto) throws BadRequestException;

    /**
     * @param personDtoList .
     * @return List of Persons updated .
     */
    List<PersonDto> updatePersons(List<PersonDto> personDtoList) throws BadRequestException;

    /**
     * @param personDtoList .
     * @return List of deleted Person Objects .
     */
    List<PersonDto> deletePersons(List<PersonDto> personDtoList) throws BadRequestException;

}
