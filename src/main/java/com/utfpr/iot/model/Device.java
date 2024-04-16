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
@Table(name = "tb_device") // Define o noma da tabel
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "enderecoIP", nullable = false, unique = true)
    private String enderecoIP;

    @Column(name = "localizacao")
    private String localizacao;
    
    @ManyToOne
    private Gateway gateway;

    @OneToMany(mappedBy = "device")
    private List<Actuator> actuators;

    @OneToMany(mappedBy = "device")
    private List<Sensor> sensors;
}
