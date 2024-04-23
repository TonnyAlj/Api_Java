package com.utfpr.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utfpr.iot.dto.DeviceDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Device;
import com.utfpr.iot.services.DeviceService;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DeviceDTO dto) {
        try {
            var res = deviceService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        var gateway = deviceService.getById(id);
        
        return gateway.isPresent()
            ? ResponseEntity.ok().body(gateway.get())
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody DeviceDTO dto) {
        try {
            return ResponseEntity.ok().body(deviceService.update(id, dto));
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        try {
            deviceService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

