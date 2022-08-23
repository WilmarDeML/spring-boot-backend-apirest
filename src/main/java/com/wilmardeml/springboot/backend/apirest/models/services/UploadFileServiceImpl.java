package com.wilmardeml.springboot.backend.apirest.models.services;

import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Override
    public Resource cargar(String nombreArchivo) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreArchivo);
        logger.info(rutaArchivo.toString());
        Resource recurso = new UrlResource(rutaArchivo.toUri());
        if (!recurso.exists() && !recurso.isReadable()) {
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
            recurso = new UrlResource(rutaArchivo.toUri());
            logger.error("Error, no se pudo cargar la imagen: ".concat(nombreArchivo));
        }
        return recurso;
    }

    @Override
    public void copiar(MultipartFile archivo, String nombreArchivo) throws IOException {
        Path rutaArchivo = getPath(nombreArchivo);
        logger.info(rutaArchivo.toString());
        Files.copy(archivo.getInputStream(), rutaArchivo);
    }

    @Override
    public void eliminar(String nombreArchivo) {
        if (nombreArchivo != null && !nombreArchivo.isBlank()) {
            Path rutaFotoAnterior = getPath(nombreArchivo);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
            }
        }
    }

    @Override
    public Path getPath(String nombreArchivo) {
        String DIRECTORIO_UPLOAD = "uploads";
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreArchivo).toAbsolutePath();
    }
}
