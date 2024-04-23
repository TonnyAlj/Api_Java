package com.utfpr.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{

}
