package com.utfpr.iot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Gateway;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    public List<Gateway> findByPersonId(long id);
}
