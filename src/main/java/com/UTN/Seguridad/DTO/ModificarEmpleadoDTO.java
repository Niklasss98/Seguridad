package com.UTN.Seguridad.DTO;


import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Enumeraciones.RolEmpleado;
import lombok.Data;

import java.util.List;

@Data
public class ModificarEmpleadoDTO {
    Long IdEmpleado;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilio;
    String contrasena;
    RolEmpleado rol;
}
