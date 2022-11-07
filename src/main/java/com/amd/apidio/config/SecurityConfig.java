package com.amd.apidio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Essa classe disponibilizará as caracteristicas de uma chave privada que será utilizada no momento
 * da criptografia na aplicação.
 * Ela possui informações das credenciais para geração do token.
 * As informações PREFIX, KEY e EXPIRATION existirão dentro do Application.properties
 */
@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    public static String PREFIX;
    public static String KEY;
    public static Long EXPIRATION;

    public void setPrefix(String prefix) {
        PREFIX = prefix;
    }

    public void setKey(String Key) {
        KEY = Key;
    }

    public void setExpiration(Long expiration) {
        EXPIRATION = expiration;
    }
}
