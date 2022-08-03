package com.wilmardeml.springboot.backend.apirest.models.services;

import java.util.List;

import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {

	List<Cliente> findAll();

	Page<Cliente> findAll(Pageable pageable);

	Cliente findById(Long id);

	Cliente save(Cliente cliente);

	Cliente update(Long id, Cliente cliente);

	void delete(Long id);

	Cliente upload(Long id, String nombreArchivo);
}
