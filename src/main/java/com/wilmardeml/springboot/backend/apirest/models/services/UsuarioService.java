package com.wilmardeml.springboot.backend.apirest.models.services;

import com.wilmardeml.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.wilmardeml.springboot.backend.apirest.models.entity.Usuario;
import com.wilmardeml.springboot.backend.apirest.models.services.interfaces.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            logger.error("Error en el login: no existe el usuario '".concat(username).concat("' en el sistema!"));
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '".concat(username).concat("' en el sistema!"));
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Role: ".concat(authority.getAuthority())))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
}
