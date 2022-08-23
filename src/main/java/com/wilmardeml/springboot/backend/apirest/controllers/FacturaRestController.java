package com.wilmardeml.springboot.backend.apirest.controllers;

import com.wilmardeml.springboot.backend.apirest.models.entity.Factura;
import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping("api")
public class FacturaRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id) {
        return clienteService.findFacturaById(id);
    }
}
