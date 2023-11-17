package com.UTN.Seguridad.Controladores;


import com.UTN.Seguridad.DTO.ClienteDTO;
import com.UTN.Seguridad.DTO.ModificarClienteDTO;
import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Servicios.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clientes")

public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl>{
    @Autowired
    ClienteServiceImpl service;
    @GetMapping("/mostrarclientes")
    public ResponseEntity<?> mostrarClientes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.mostrarClientes());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/modificarCliente")
    public ResponseEntity<?> modificarCliente(ModificarClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.modificarCliente(clienteDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verDatos")
    public ResponseEntity<?> verDatosCliente(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/modificarDatos")      //Esto creo q es un @PostMapping pero no se
    public ResponseEntity<?> modificarDatosCliente(@RequestBody ClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
