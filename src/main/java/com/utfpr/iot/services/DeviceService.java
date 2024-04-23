package com.utfpr.iot.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.iot.dto.DeviceDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Device;
import com.utfpr.iot.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device create(DeviceDTO dto) {
        Device device = new Device();
        BeanUtils.copyProperties(dto, device);
        return deviceRepository.save(device);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(long id) {
        return deviceRepository.findById(id);
    }

    public Device update(long id, DeviceDTO dto) throws NotFoundException {
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        Device device = optionalDevice.orElseThrow(() -> new NotFoundException("Dispositivo não encontrado"));
        BeanUtils.copyProperties(dto, device, "id");
        return deviceRepository.save(device);
    }

    public void delete(long id) throws NotFoundException {
        if (!deviceRepository.existsById(id)) {
            throw new NotFoundException("Dispositivo não encontrado");
        }
        deviceRepository.deleteById(id);
    }
}

