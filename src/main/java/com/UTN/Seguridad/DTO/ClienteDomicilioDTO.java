package com.UTN.Seguridad.DTO;

import lombok.Data;

@Data
public class ClienteDomicilioDTO {
    Long id;
    String calle;
    String numero;
    String localidad;
}
