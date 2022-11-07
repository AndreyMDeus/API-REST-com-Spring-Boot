package com.amd.apidio.resources;

import com.amd.apidio.domain.Usuario;
import com.amd.apidio.repositories.UsuarioRepository;
import com.amd.apidio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public void postUsuario(@RequestBody Usuario usuario){
        usuarioService.criaUsuario(usuario);
    }
}
