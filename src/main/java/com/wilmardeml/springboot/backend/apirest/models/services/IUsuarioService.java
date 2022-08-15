package com.wilmardeml.springboot.backend.apirest.models.services;

import com.wilmardeml.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);
}
