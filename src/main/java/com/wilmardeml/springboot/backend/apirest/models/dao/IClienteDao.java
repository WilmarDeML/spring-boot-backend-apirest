package com.wilmardeml.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
