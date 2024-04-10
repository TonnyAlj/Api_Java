package com.utfpr.iot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Entidade gerenciada pelo JPA (pode ser persistida)
@Table(name = "tb_actuator") // Define o noma da tabel
@Data
public class Actuator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    

}
