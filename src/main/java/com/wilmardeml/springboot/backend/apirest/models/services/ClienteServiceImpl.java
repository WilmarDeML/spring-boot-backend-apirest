package com.wilmardeml.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilmardeml.springboot.backend.apirest.models.dao.IClienteDao;
import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	IUploadFileService uploadFileService;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		Cliente foundClient = this.findById(id);
		if (foundClient != null) {
			foundClient.setNombre(cliente.getNombre());
			foundClient.setApellido(cliente.getApellido());
			foundClient.setEmail(cliente.getEmail());
			foundClient.setCreateAt(cliente.getCreateAt());
			return this.save(foundClient);
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Cliente cliente = this.findById(id);
		if (cliente != null) {
			String nombreFotoAnterior = cliente.getFoto();
			uploadFileService.eliminar(nombreFotoAnterior);
			clienteDao.deleteById(id);
		}
	}

	@Override
	@Transactional
	public Cliente upload(Long id, String nombreFoto) {
		Cliente cliente = this.findById(id);
		if (cliente != null) {
			String nombreFotoAnterior = cliente.getFoto();
			uploadFileService.eliminar(nombreFotoAnterior);
			cliente.setFoto(nombreFoto);
			return this.save(cliente);
		}
		return null;
	}

}
