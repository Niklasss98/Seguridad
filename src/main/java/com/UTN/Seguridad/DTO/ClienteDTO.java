package com.UTN.Seguridad.DTO;


import com.UTN.Seguridad.Entidades.Domicilio;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    Long idCliente;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilio;
    String contrasena;

}
