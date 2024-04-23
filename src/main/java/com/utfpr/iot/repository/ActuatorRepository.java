package com.utfpr.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Actuator;

public interface ActuatorRepository extends JpaRepository<Actuator, Long>{

}
