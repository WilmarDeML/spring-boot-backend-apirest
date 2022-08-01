package com.wilmardeml.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;
import com.wilmardeml.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("clientes")
	public List<Cliente> listAll() {
		return clienteService.findAll();
	}

	@GetMapping("clientes/{id}")
	public ResponseEntity<?> listById(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente cliente = clienteService.findById(id);
			if (cliente == null) {
				respuesta.put("mensaje", "El cliente con id ".concat(id.toString().concat(" no existe en base de datos!")));
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al realizar la consulta en base de datos");
			respuesta.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) { return clienteService.save(cliente); }

	@PutMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
		return  clienteService.update(id, cliente);
	}

	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) { clienteService.delete(id); }

}
