package com.service.person.resource;

import com.service.person.dto.PersonDto;
import com.service.person.dto.PersonsDto;
import com.service.person.exception.BadRequestException;
import com.service.person.services.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController
@Validated
@RequestMapping("/person")
public class PersonResource {

    @Autowired
    private IPerson personService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") String id) {
        PersonDto personDto = personService.getById(id);
        if(Objects.isNull(personDto)){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDto);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonDto person) {
        PersonDto personDto = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.OK).body(personDto);
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonDto person) throws BadRequestException {
        PersonDto personDto = personService.updatePerson(person);
        return ResponseEntity.status(HttpStatus.OK).body(personDto);
    }

    @PostMapping("/batch/")
    public ResponseEntity<?> createPersons(@RequestBody @Valid List<PersonDto> person) {
        List<PersonDto> personDtos = personService.createPersons(person);
        if(Objects.isNull(personDtos) || personDtos.isEmpty()){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDtos);
    }

    @PutMapping("/batch/")
    public ResponseEntity<?> updatePersons(@RequestBody @Valid List<PersonDto> person) throws BadRequestException {
        List<PersonDto> personDtos = personService.updatePersons(person);
        if(Objects.isNull(personDtos) || personDtos.isEmpty()){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDtos);
    }

    @DeleteMapping("/batch/")
    public ResponseEntity<?> deletePersons(@RequestBody @Valid List<PersonDto> person) throws BadRequestException {
        List<PersonDto> personDtos = personService.deletePersons(person);
        if(Objects.isNull(personDtos) || personDtos.isEmpty()){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDtos);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPersons(String id, @RequestParam(required = false) Integer page) {
        PersonsDto personsDto = personService.getAllPersons(page);
        if(Objects.isNull(personsDto.getPerson()) || personsDto.getPerson().isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(personsDto);
    }
}
