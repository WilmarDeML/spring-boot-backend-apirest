package com.wilmardeml.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import com.wilmardeml.springboot.backend.apirest.models.entity.Region;
import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;
import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IClienteService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

    @Autowired
    IUploadFileService uploadFileService;

	@GetMapping("clientes")
	public List<Cliente> listAll() {
		return clienteService.findAll();
	}

	@GetMapping("clientes/page/{page}")
	public Page<Cliente> listAll(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	// @Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> listById(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente cliente = clienteService.findById(id);
			if (cliente == null) {
				respuesta.put("mensaje", "El cliente con id ".concat(id.toString().concat(" no existe en base de datos!")));
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al realizar la consulta en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> respuesta = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(
					error -> "El campo '".concat(error.getField()).concat("' ".concat(Objects.requireNonNull(error.getDefaultMessage())))
			).collect(Collectors.toList());
			respuesta.put("errores", errors);
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		try {
			Cliente clienteNuevo = clienteService.save(cliente);
			respuesta.put("mensaje", "El cliente ha sido creado con éxito!");
			respuesta.put("cliente", clienteNuevo);
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al realizar el insert en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(
					error -> "El campo '".concat(error.getField()).concat("' ".concat(Objects.requireNonNull(error.getDefaultMessage())))
			).collect(Collectors.toList());
			respuesta.put("errores", errors);
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			Cliente clienteActualizado = clienteService.update(id, cliente);
			if (cliente == null) {
				respuesta.put("mensaje", "El cliente que desea editar, con id ".concat(id.toString().concat(" no existe en base de datos!")));
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			}
			respuesta.put("mensaje", "El cliente ha sido actualizado con éxito!");
			respuesta.put("cliente", clienteActualizado);
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al actaulizar cliente en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			clienteService.delete(id);
			respuesta.put("mensaje", "El cliente ha sido eliminado con éxito!");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al eliminar cliente en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			if (!archivo.isEmpty()) {
				String nombreArchivoFoto = UUID.randomUUID().toString().concat("_".concat(Objects.requireNonNull(archivo.getOriginalFilename()).replace(" ", "")));
				Cliente cliente = clienteService.upload(id, nombreArchivoFoto);
				if (cliente == null) {
					respuesta.put("mensaje", "El cliente que desea editar, con id ".concat(id.toString().concat(" no existe en base de datos!")));
					return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
				}
                uploadFileService.copiar(archivo, nombreArchivoFoto);
				respuesta.put("cliente", cliente);
				respuesta.put("mensaje", "Has subido correctamente la imagen: ".concat(nombreArchivoFoto));
			}
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al actaulizar la imagen en base de datos");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			respuesta.put("mensaje", "Error al subir la imagen del cliente");
			respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getCause().getMessage())));
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        try {
			Resource recurso = uploadFileService.cargar(nombreFoto);
			HttpHeaders cabecera = new HttpHeaders();
            cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(Objects.requireNonNull(recurso.getFilename()).concat("\"")));
            return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

	@Secured("ROLE_ADMIN")
	@GetMapping("clientes/regiones")
	public List<Region> listarRegiones() {
		return clienteService.findAllRegiones();
	}
}
