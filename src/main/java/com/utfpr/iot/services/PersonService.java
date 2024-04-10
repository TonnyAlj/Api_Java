package com.utfpr.iot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.utfpr.iot.dto.PersonDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Person;
import com.utfpr.iot.repository.PersonRepository;

public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    /**
     * Inserir uma pessoa no DB.
     * @return
     */
    public Person create(PersonDTO dto) {
        var pessoa = new Person();
        BeanUtils.copyProperties(dto, pessoa);

        // Persistir no Banco de dados
        return personRepository.save(pessoa);
    }

    /**
     * Buscar no banco de dados todas.
     * @return
     */
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    /**
     * Buscar uma pessoa pelo ID.
     * @param id
     * @return
     */
    public Optional<Person> getById(long id) {
        return personRepository.findById(id);
    }


    public Person update(long id, PersonDTO dto) throws NotFoundException {
        var res = personRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        var pessoa = res.get();
        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());

        return personRepository.save(pessoa);
    }

    public void delete(long id) throws NotFoundException {
        var res = personRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        personRepository.delete(res.get());
    }
}
