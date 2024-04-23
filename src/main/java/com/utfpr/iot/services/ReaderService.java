package com.utfpr.iot.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.iot.dto.ReaderDTO;
import com.utfpr.iot.exception.NotFoundException;
import com.utfpr.iot.model.Reader;
import com.utfpr.iot.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public Reader create(ReaderDTO dto) {
        Reader reader = new Reader();
        BeanUtils.copyProperties(dto, reader);
        return readerRepository.save(reader);
    }

    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getById(long id) {
        return readerRepository.findById(id);
    }

    public Reader update(long id, ReaderDTO dto) throws NotFoundException {
        Optional<Reader> optionalReader = readerRepository.findById(id);
        Reader reader = optionalReader.orElseThrow(() -> new NotFoundException("Leitor não encontrado"));
        BeanUtils.copyProperties(dto, reader, "id");
        return readerRepository.save(reader);
    }

    public void delete(long id) throws NotFoundException {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Leitor não encontrado");
        }
        readerRepository.deleteById(id);
    }
}

