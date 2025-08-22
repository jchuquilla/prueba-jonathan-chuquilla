package com.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients", schema = "GSTPROD")
public class Client {

    @Id
    @SequenceGenerator(name = "clients_seq_generator", sequenceName = "gstprod.clients_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_seq_generator")
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identification")
    private String identificationNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "email")
    private String email;

}
