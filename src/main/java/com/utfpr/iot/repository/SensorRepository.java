package com.utfpr.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long>{

}
