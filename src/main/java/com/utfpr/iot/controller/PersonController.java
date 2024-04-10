package com.utfpr.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.utfpr.iot.dto.PersonDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Person;
import com.utfpr.iot.services.PersonService;

public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PersonDTO dto) {
        try {
            var res = personService.create(dto);
            // Seta o status para 201 (CREATED) e devolve
            // o objeto Pessoa em JSON.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            // Seta o status para 400 (Bad request) e devolve
            // a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Obter todas as pessoas do DB.
     */
    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    /**
     * Obter 1 pessoa pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var person = personService.getById(id);
        
        return person.isPresent()
            ? ResponseEntity.ok().body(person.get())
            : ResponseEntity.notFound().build();
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,
        @RequestBody PersonDTO dto) {
            try {
                return ResponseEntity.ok().body(personService.update(id, dto));
            } catch(NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } catch(Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            personService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
