package com.utfpr.iot.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.iot.dto.GatewayDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Gateway;
import com.utfpr.iot.repository.GatewayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    public Gateway create(GatewayDTO dto) {
        Gateway gateway = new Gateway();
        BeanUtils.copyProperties(dto, gateway);
        return gatewayRepository.save(gateway);
    }

    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    public Optional<Gateway> getById(long id) {
        return gatewayRepository.findById(id);
    }

    public Gateway update(long id, GatewayDTO dto) throws NotFoundException {
        Optional<Gateway> optionalGateway = gatewayRepository.findById(id);
        Gateway gateway = optionalGateway.orElseThrow(() -> new NotFoundException("Gateway não encontrado"));
        BeanUtils.copyProperties(dto, gateway, "id");
        return gatewayRepository.save(gateway);
    }

    public void delete(long id) throws NotFoundException {
        if (!gatewayRepository.existsById(id)) {
            throw new NotFoundException("Gateway não encontrado");
        }
        gatewayRepository.deleteById(id);
    }
}

