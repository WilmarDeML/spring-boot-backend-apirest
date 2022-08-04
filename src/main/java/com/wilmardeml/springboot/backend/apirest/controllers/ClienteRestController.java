package com.wilmardeml.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.wilmardeml.springboot.backend.apirest.models.entity.Cliente;
import com.wilmardeml.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api")
public class ClienteRestController {

	private final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
	@Autowired
	private IClienteService clienteService;

	@GetMapping("clientes")
	public List<Cliente> listAll() {
		return clienteService.findAll();
	}

	@GetMapping("clientes/page/{page}")
	public Page<Cliente> listAll(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}
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

	@PutMapping("clientes/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody Cliente cliente, BindingResult result) {
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

	@PostMapping("clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			if (!archivo.isEmpty()) {
				String nombreArchivo = UUID.randomUUID().toString().concat("_".concat(Objects.requireNonNull(archivo.getOriginalFilename()).replace(" ", "")));
				Cliente cliente = clienteService.upload(id, nombreArchivo);
				if (cliente == null) {
					respuesta.put("mensaje", "El cliente que desea editar, con id ".concat(id.toString().concat(" no existe en base de datos!")));
					return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
				}
				Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
				logger.info(rutaArchivo.toString());
				Files.copy(archivo.getInputStream(), rutaArchivo);
				respuesta.put("cliente", cliente);
				respuesta.put("mensaje", "Has subido correctamente la imagen: ".concat(nombreArchivo));
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
        	Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			logger.info(rutaArchivo.toString());
			Resource recurso = new UrlResource(rutaArchivo.toUri());
            if (!recurso.exists() && !recurso.isReadable()) {
                throw new RuntimeException("Error, no se pudo cargar la imagen: ".concat(nombreFoto));
            }
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(Objects.requireNonNull(recurso.getFilename()).concat("\"")));
            return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
