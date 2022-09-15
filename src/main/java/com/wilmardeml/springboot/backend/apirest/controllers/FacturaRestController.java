package com.wilmardeml.springboot.backend.apirest.controllers;

import com.wilmardeml.springboot.backend.apirest.models.entity.Factura;
import com.wilmardeml.springboot.backend.apirest.models.entity.Producto;
import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.deleteFacturaById(id);
    }

    @GetMapping("facturas/filtrar-productos/{term}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable String term) {
        return clienteService.findProductoByNombre(term);
    }
}
