package com.wilmardeml.springboot.backend.apirest.models.dao;

import com.wilmardeml.springboot.backend.apirest.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    @Query("select u from Usuario u where u.username = ?1")
    Usuario findByUsername2(String username);

}
