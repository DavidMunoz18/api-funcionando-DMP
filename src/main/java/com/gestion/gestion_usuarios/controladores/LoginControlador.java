package com.gestion.gestion_usuarios.controladores;

import com.gestion.gestion_usuarios.dto.Usuario;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Endpoint para autenticar usuario
    @PostMapping("/validar")
    public ResponseEntity<String> autenticarUsuario(@RequestBody Usuario usuario) {
        System.out.println("Email recibido: " + usuario.getEmail());
        System.out.println("Contraseña recibida: " + usuario.getPassword());

        // Llamamos al servicio para validar las credenciales
        ResponseEntity<String> resultado = usuarioServicio.validarCredenciales(usuario.getEmail(), usuario.getPassword());

        // Verificamos si las credenciales son correctas
        if (resultado.getStatusCodeValue() == 401) {
            System.out.println("Credenciales incorrectas");
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos.");
        }

        // Comprobamos el rol directamente
        String rol = resultado.getBody().trim();  // Obtenemos el cuerpo de la respuesta

        System.out.println("Rol del usuario: " + rol);

        if ("admin".equals(rol)) {
            return ResponseEntity.status(200).body("admin");
        } else if ("usuario".equals(rol)) {
            return ResponseEntity.status(200).body("usuario");
        }

        return ResponseEntity.status(401).body("Rol desconocido.");
    }
}
