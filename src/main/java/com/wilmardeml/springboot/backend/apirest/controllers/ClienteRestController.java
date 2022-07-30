package com.wilmardeml.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public Cliente listById(@PathVariable Long id) { return  clienteService.findById(id); }

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
