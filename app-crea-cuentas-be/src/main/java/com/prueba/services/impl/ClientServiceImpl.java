package com.prueba.services.impl;

import com.prueba.Utils.Utils;
import com.prueba.dtos.ClientAccountDTO;
import com.prueba.entities.Account;
import com.prueba.entities.Client;
import com.prueba.repositories.AccountRepo;
import com.prueba.repositories.ClientRepo;
import com.prueba.repositories.GenericRepo;
import com.prueba.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements ClientService {

    private final ClientRepo clientRepo;
    private final AccountRepo accountRepo;

    @Override
    protected GenericRepo<Client, Long> getRepo() {
        return clientRepo;
    }

    @Override
    @Transactional
    public List<String> clientsLoad(MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new Error("El archivo no puede ser vacio");
        }

        List<Client> clients = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");

                if(data.length != 6){
                    throw new IllegalArgumentException("Error en linea " + line + ": El archivo no tiene el formato esperado");
                }

                if(!Utils.isValidDate(data[4])){
                    throw new IllegalArgumentException("Error en linea " + line + ": Fecha no tiene el formato esperado");
                }

                if (!Utils.isValidEmail(data[5])){
                    throw new IllegalArgumentException("Error en linea " + line + ": Email no tiene el formato esperado");
                }

                if(!Utils.isValidIdentificationNumber(data[2])){
                    throw new IllegalArgumentException("Error en linea " + line + ": Número de cédula incorrecto");
                }

                Client client = new Client();
                client.setFirstName(data[0]);
                client.setLastName(data[1]);
                client.setIdentificationNumber(data[2]);
                client.setAge(Integer.parseInt(data[3]));
                client.setRegistrationDate(Utils.parseDate(data[4]));
                client.setEmail(data[5]);

                clients.add(client);

            }

            var clientsSaved = clientRepo.saveAll(clients);

            clientsSaved.forEach(client -> {
                //Luego de crear cliente creamos cuenta
                Account account = new Account();
                account.setAccountNumber(generateAccountNumber());
                account.setBalance(BigDecimal.ZERO);
                account.setClient(client);
                account.setCreatedDate(LocalDateTime.now());
                accounts.add(account);
            });
            accountRepo.saveAll(accounts);
        }catch (IllegalArgumentException ex){
            errors.add(ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }catch (Exception ex){
            errors.add(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        return errors;
    }

    @Override
    public List<ClientAccountDTO> getClientAccounts() {
        var resp = clientRepo.getClientsAccounts()
                .stream()
                .map(tuple -> new ClientAccountDTO(
                        tuple.get(0, String.class),
                        tuple.get(1,String.class),
                        tuple.get(2, String.class),
                        tuple.get(3, Integer.class),
                        tuple.get(4, String.class),
                        tuple.get(5, String.class),
                        tuple.get(6, Timestamp.class).toLocalDateTime()
                )).toList();
        return resp;
    }

    private String generateAccountNumber() {
        return String.valueOf((long)(Math.random() * 1_000_000_000));
    }
}
