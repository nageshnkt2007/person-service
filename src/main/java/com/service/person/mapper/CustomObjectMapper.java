package com.service.person.mapper;

import com.service.person.dto.PersonDto;
import com.service.person.entity.Person;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * The interface Custom object mapper.
 */
@Component
@Mapper(componentModel = "spring")
public interface CustomObjectMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);

    List<PersonDto> personsToPersonDtos(List<Person> personList);

    List<Person> personDtosToPersons(List<PersonDto> personList);
}
