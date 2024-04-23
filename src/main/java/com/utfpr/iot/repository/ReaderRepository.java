package com.utfpr.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long>{

}
