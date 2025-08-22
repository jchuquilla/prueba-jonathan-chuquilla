package com.prueba.dtos;

import com.prueba.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdDate;
    private Client client;

}
