package com.utfpr.iot.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Entidade gerenciada pelo JPA (pode ser persistida)
@Table(name = "tb_sensor") // Define o noma da tabel
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
 
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    private Device device;

    @OneToMany(mappedBy = "sensor")
    private List<Reader> readers;
}
