package com.prueba.dtos;

import com.prueba.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountDTO {

    private String firstName;
    private String lastName;
    private String identificationNumber;
    private Integer age;
    private String email;
    private String accountNumber;
    private LocalDateTime createdDate;

}
