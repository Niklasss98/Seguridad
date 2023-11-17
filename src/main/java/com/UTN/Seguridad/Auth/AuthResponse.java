package com.UTN.Seguridad.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {  //DTO de ingreso a la pag, cuando el usuario ya esta registrado y logueado a la pag, devuelve un token
    String token;
}