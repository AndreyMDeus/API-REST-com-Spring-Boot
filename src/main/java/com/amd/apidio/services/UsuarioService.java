package com.amd.apidio.services;

import com.amd.apidio.domain.Usuario;
import com.amd.apidio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classse que disponibiliza os serviços para o usuário
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void criaUsuario(Usuario usuario) {
        String senha = usuario.getSenha();
        /**
         * Criptografando antes de salvar no banco
         */
        usuario.setSenha(passwordEncoder.encode(senha));
        usuarioRepository.save(usuario);
    }

}
