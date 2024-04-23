package com.utfpr.iot.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.iot.dto.ActuatorDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Actuator;
import com.utfpr.iot.repository.ActuatorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActuatorService {

    @Autowired
    private ActuatorRepository actuatorRepository;

    public Actuator create(ActuatorDTO dto) {
        Actuator actuator = new Actuator();
        BeanUtils.copyProperties(dto, actuator);
        return actuatorRepository.save(actuator);
    }

    public List<Actuator> getAll() {
        return actuatorRepository.findAll();
    }

    public Optional<Actuator> getById(long id) {
        return actuatorRepository.findById(id);
    }

    public Actuator update(long id, ActuatorDTO dto) throws NotFoundException {
        Optional<Actuator> optionalActuator = actuatorRepository.findById(id);
        Actuator actuator = optionalActuator.orElseThrow(() -> new NotFoundException("Atuador não encontrado"));
        BeanUtils.copyProperties(dto, actuator, "id");
        return actuatorRepository.save(actuator);
    }

    public void delete(long id) throws NotFoundException {
        if (!actuatorRepository.existsById(id)) {
            throw new NotFoundException("Atuador não encontrado");
        }
        actuatorRepository.deleteById(id);
    }
}
