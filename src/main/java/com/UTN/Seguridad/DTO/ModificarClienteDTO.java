package com.UTN.Seguridad.DTO;

import lombok.Data;

@Data
public class ModificarClienteDTO {
    Long idCliente;
    String nombre;
    String apellido;
    String telefono;
    String email;
}
