package com.prueba.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String identificationNumber;
    private Integer age;
    private LocalDate registrationDate;
    private String email;

}
