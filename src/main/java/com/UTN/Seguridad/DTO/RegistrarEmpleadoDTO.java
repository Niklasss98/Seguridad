package com.UTN.Seguridad.DTO;


import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Enumeraciones.RolEmpleado;
import com.UTN.Seguridad.User.RolUsuario;
import lombok.Data;

import java.util.List;

@Data
public class RegistrarEmpleadoDTO {
    //nombre, apellido, dirección, departamento, teléfono, 
    //email y una clave provisoria, la cual el empleado, al tener acceso por primera vez, tendrá que modificar obligatoriamente.
    //SeleccionarROL
    //que no exista un empleado registrado con la misma direccion de email
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilio;
    String contrasena;
    RolUsuario rol;





}
