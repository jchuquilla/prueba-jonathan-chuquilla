package com.prueba.controllers;

import com.prueba.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/load")
    public ResponseEntity<?> loadClients(@RequestParam("file") MultipartFile file) {
        var resp = clientService.clientsLoad(file);
        return ResponseEntity.ok("Clientes cargados exitosamente");
    }

}
