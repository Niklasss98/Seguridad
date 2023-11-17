package com.UTN.Seguridad.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor        //Obliga al constructor a pedir todos los argumentos
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login") //no puedo mandar un body con get
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "registerCliente")
    public ResponseEntity<AuthResponse> registerCliente(@RequestBody RegisterClienteRequest request)
    {
        return ResponseEntity.ok(authService.registerCliente(request));
    }

    @PostMapping(value = "registerEmpleado")
    public ResponseEntity<AuthResponse> registerEmpleado(@RequestBody RegisterEmpleadoRequest request)
    {
        return ResponseEntity.ok(authService.registerEmpleado(request));
    }
}

