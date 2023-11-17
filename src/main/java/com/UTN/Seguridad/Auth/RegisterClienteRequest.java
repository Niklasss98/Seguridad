package com.UTN.Seguridad.Auth;

import com.UTN.Seguridad.Entidades.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClienteRequest {
    String nombre;
    String password;
    String apellido;
    String username;
    String telefono;
    String email;
    //List<Domicilio> domicilios;
}
