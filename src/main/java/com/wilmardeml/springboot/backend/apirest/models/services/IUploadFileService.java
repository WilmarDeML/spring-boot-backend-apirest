package com.wilmardeml.springboot.backend.apirest.models.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {
    Resource cargar(String nombreFoto) throws MalformedURLException;

    void copiar(MultipartFile archivo, String nombreArchivo) throws IOException;

    void eliminar(String nombreFoto);

    Path getPath(String nombreFoto);
}
