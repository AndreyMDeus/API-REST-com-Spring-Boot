package com.amd.apidio.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException {

    public LoginException(String usuario) {

        super("Senha inválida para o login: " + usuario + "!");

    }

    public LoginException() {

        super("Erro ao tentar fazer login, usuário inválido!");

    }

}
