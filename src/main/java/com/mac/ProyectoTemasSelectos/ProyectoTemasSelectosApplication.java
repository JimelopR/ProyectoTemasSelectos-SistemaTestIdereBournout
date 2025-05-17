package com.mac.ProyectoTemasSelectos;

import com.mac.ProyectoTemasSelectos.models.UsuarioModel;
import com.mac.ProyectoTemasSelectos.repositories.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ProyectoTemasSelectosApplication {




	public static void main(String[] args) {
		SpringApplication.run(ProyectoTemasSelectosApplication.class, args);
                 System.out.println("Aplicación de Spring Boot iniciada...");
                  //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                  //String contrasenaOriginal = "SistemaTemasSelectos123";
                  //String contrasenaEncriptada = passwordEncoder.encode(contrasenaOriginal);
                  //System.out.println("contrasenaOriginal: "+contrasenaOriginal);
                  //System.out.println("contrasenaEncriptada: "+contrasenaEncriptada);
        }
}
