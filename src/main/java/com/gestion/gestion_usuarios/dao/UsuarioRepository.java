package com.gestion.gestion_usuarios.dao;

import com.gestion.gestion_usuarios.entity.Usuario;  // Asegúrate de que sea la entidad
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);  // Buscar usuario por email
}
