package com.amd.apidio.resources;

import com.amd.apidio.config.SecurityConfig;
import com.amd.apidio.domain.Usuario;
import com.amd.apidio.dto.LoginDTO;
import com.amd.apidio.dto.SessaoDTO;
import com.amd.apidio.repositories.UsuarioRepository;
import com.amd.apidio.resources.exception.LoginException;
import com.amd.apidio.security.JWTCreator;
import com.amd.apidio.security.JWTObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class LoginResource {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public SessaoDTO logar(@RequestBody LoginDTO login){
        Usuario usuario = usuarioRepository.findByNomeUsuario(login.getNomeUsuario());
        if (usuario!=null) {
            boolean passwordOk = encoder.matches(login.getSenha(), usuario.getSenha());
            if (!passwordOk) {
               /* throw new RuntimeException("Senha inválida para o login: " + login.getNomeUsuario()); */
                throw new LoginException(login.getNomeUsuario());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            SessaoDTO sessao = new SessaoDTO();
            sessao.setLogin(usuario.getNomeUsuario());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(usuario.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        } else {
/*            throw new RuntimeException("Erro ao tentar fazer login"); */
            throw new LoginException();
        }
    }
}

