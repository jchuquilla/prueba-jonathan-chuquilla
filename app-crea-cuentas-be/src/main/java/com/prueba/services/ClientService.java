package com.prueba.services;

import com.prueba.entities.Client;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientService extends GenericService<Client, Long> {

    public List<String> clientsLoad(MultipartFile file) throws Exception;

}
