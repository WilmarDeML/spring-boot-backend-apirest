package com.wilmardeml.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente clienteNuevo = clienteService.save(cliente);
			respuesta.put("mensaje", "El cliente ha sido creado con éxito!");
			respuesta.put("cliente", clienteNuevo);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al realizar el insert en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("clientes/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente clienteActualizado = clienteService.update(id, cliente);
			if (cliente == null) {
				respuesta.put("mensaje", "El cliente que desea editar, con id ".concat(id.toString().concat(" no existe en base de datos!")));
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
			respuesta.put("mensaje", "El cliente ha sido actualizado con éxito!");
			respuesta.put("cliente", clienteActualizado);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al actaulizar cliente en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			clienteService.delete(id);
			respuesta.put("mensaje", "El cliente ha sido eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al eliminar cliente en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
