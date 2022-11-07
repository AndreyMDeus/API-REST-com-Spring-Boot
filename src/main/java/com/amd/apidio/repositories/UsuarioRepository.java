package com.amd.apidio.repositories;

import com.amd.apidio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.nomeUsuario= (:nomeUsuario)")

    public Usuario findByNomeUsuario(@Param("nomeUsuario") String nomeUsuario);

    boolean existsByNomeUsuario(String nomeUsuario);
}

