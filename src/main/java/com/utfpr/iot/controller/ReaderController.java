package com.utfpr.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utfpr.iot.dto.ReaderDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Reader;
import com.utfpr.iot.services.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ReaderDTO dto) {
        try {
            var res = readerService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Reader> getAll() {
        return readerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        var gateway = readerService.getById(id);
        
        return gateway.isPresent()
            ? ResponseEntity.ok().body(gateway.get())
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody ReaderDTO dto) {
        try {
            return ResponseEntity.ok().body(readerService.update(id, dto));
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        try {
            readerService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

