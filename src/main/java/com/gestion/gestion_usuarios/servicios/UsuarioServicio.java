package com.gestion.gestion_usuarios.servicios;

import com.gestion.gestion_usuarios.dao.UsuarioRepository;
import com.gestion.gestion_usuarios.entity.Usuario;  // Cambiar DTO a entidad
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyectamos el repositorio

    public ResponseEntity<String> validarCredenciales(String email, String password) {
        // Intentamos recuperar al usuario por su email
        Usuario usuario = usuarioRepository.findByEmail(email); // Usamos la entidad Usuario

        if (usuario == null) {
            System.out.println("Usuario no encontrado para el email: " + email);
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        System.out.println("Usuario encontrado: " + usuario.getEmail());
        System.out.println("Contraseña almacenada: " + usuario.getPassword());
        System.out.println("Contraseña recibida: " + password);

        // Aquí comparamos la contraseña directamente
        if (!password.equals(usuario.getPassword())) {
            System.out.println("Contraseña incorrecta");
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // Si las credenciales son correctas, devolvemos el rol
        String rol = "usuario";  // Suponemos que por defecto todos los usuarios son "usuario"
        if (usuario.getEmail().equals("admin@dominio.com")) {
            rol = "admin";  // Si el email es el de un admin, asignamos el rol "admin"
        }

        return ResponseEntity.ok(rol);  // Devolvemos el rol
    }
}
