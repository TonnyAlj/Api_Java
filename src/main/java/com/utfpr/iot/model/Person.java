package com.utfpr.iot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Entidade gerenciada pelo JPA (pode ser persistida)
@Table(name = "tb_person") // Define o noma da tabel
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    
    @Column(nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    //@OneToMany(mappedBy = "person")
    //private List<Gateway> gateways;
}
