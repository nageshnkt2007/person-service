package com.service.person.services.impl;

import com.service.person.constants.PersonConstants;
import com.service.person.dao.PersonRepository;
import com.service.person.dto.PersonDto;
import com.service.person.dto.PersonsDto;
import com.service.person.entity.Person;
import com.service.person.exception.BadRequestException;
import com.service.person.mapper.CustomObjectMapper;
import com.service.person.services.IPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPerson {

    private final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    /**
     * PersonRepository to interact with
     * database layer .
     */
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @Value("${pagination.size:10}")
    private Integer paginationSize;

    /**
     * @param personDto .
     * @return PersonDto .
     */
    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = customObjectMapper.personDtoToPerson(personDto);
        person = personRepository.save(person);
        return customObjectMapper.personToPersonDto(person);
    }

    /**
     * @param personDto .
     * @return updated PersonDto object .
     */
    @Override
    public PersonDto updatePerson(PersonDto personDto) throws BadRequestException {
        if(Objects.isNull(personDto.getId())){
            throw new BadRequestException(PersonConstants.BAD_REQUEST_MESSAGE);
        }
        Optional<Person> personSaved = personRepository.findById(personDto.getId());
        Person person = null;
        if(personSaved.isPresent()){
            person = customObjectMapper.personDtoToPerson(personDto);
            person = personRepository.save(person);
        }else{
            throw new BadRequestException(PersonConstants.BAD_REQUEST_MESSAGE);
        }
        return Objects.nonNull(person)?customObjectMapper.personToPersonDto(person):null;
    }

    /**
     * @param id .
     * @return PersonDto .
     */
    @Override
    public PersonDto getById(String id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent()?customObjectMapper.personToPersonDto(person.get()):null;
    }

    /**
     * @return All Person Objects .
     * @param page
     */
    @Override
    public PersonsDto getAllPersons(Integer page) {
        if(Objects.isNull(page)){
            page=0;
        }
        Pageable pageable = PageRequest.of(page, paginationSize);
        Page<Person> personDB = personRepository.findAll(pageable);
        PersonsDto personsDto = new PersonsDto();
        List<Person> persons = personDB.getContent();
        personsDto.setPerson(customObjectMapper.personsToPersonDtos(persons));
        personsDto.setTotalPages(personDB.getTotalPages());
        return personsDto;
    }


    /**
     * @param personDtoList .
     * @return List of Persons saved .
     */
    @Override
    public List<PersonDto> createPersons(List<PersonDto> personDtoList) {
        List<Person> persons = customObjectMapper.personDtosToPersons(personDtoList);
        persons = (List<Person>) personRepository.saveAll(persons);
        return Objects.nonNull(persons)?customObjectMapper.personsToPersonDtos(persons):null;
    }

    /**
     * @param personDtoList .
     * @return List of Persons updated .
     */
    @Override
    public List<PersonDto> updatePersons(List<PersonDto> personDtoList) throws BadRequestException {
        LOG.info("updatePersons started ");
        if(!validPersonsData(personDtoList)){
            LOG.info("updatePersons halted for validation ");
            throw new BadRequestException(PersonConstants.BAD_REQUEST_MESSAGE);
        }
        List<Person> persons = customObjectMapper.personDtosToPersons(personDtoList);
        persons = (List<Person>) personRepository.saveAll(persons);
        LOG.info("updatePersons completed ");
        return customObjectMapper.personsToPersonDtos(persons);
    }

    /**
     * @param personDtoList .
     * @return List of deleted Person Objects .
     */
    @Override
    public List<PersonDto> deletePersons(List<PersonDto> personDtoList) throws BadRequestException {
        LOG.info("deletePersons started ");
        if(!validPersonsData(personDtoList)){
            LOG.info("deletePersons halted for validation ");
            throw new BadRequestException(PersonConstants.BAD_REQUEST_MESSAGE_DELETE);
        }
        List<Person> persons = customObjectMapper.personDtosToPersons(personDtoList);
        personRepository.deleteAll(persons);
        LOG.info("deletePersons completed ");
        return customObjectMapper.personsToPersonDtos(persons);
    }

    private Boolean validPersonsData(List<PersonDto> personDtoList) {
        if(Objects.isNull(personDtoList) || personDtoList.isEmpty()){
            return Boolean.FALSE;
        }
        List<String> personIdList = personDtoList.stream().filter(Objects::nonNull).map(PersonDto::getId).collect(Collectors.toList());
        if(personIdList.size()!=personDtoList.size()){
            return Boolean.FALSE;
        }
        List<Person> allPersonsById = (List<Person>) personRepository.findAllById(personIdList);
        if(Objects.isNull(allPersonsById) || allPersonsById.size()!=personDtoList.size()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


}
