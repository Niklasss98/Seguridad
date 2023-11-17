package com.UTN.Seguridad.Controladores;


import com.UTN.Seguridad.DTO.ClienteDomicilioDTO;
import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Servicios.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/domilicios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioServiceImpl>{
    @Autowired
    DomicilioServiceImpl service;
    @GetMapping("/mostrarDomicilioscliente")
    public ResponseEntity<?> mostrarDomiciliosCliente(Cliente cliente){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.mostrarDomiciliosCliente(cliente));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/modificarDomicilioCliente")
    public ResponseEntity<?> modificarDomicilioCliente(ClienteDomicilioDTO domicilioDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.modificarDomicilioCliente(domicilioDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
