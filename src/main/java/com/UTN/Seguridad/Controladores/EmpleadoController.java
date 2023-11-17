package com.UTN.Seguridad.Controladores;



import com.UTN.Seguridad.DTO.ModificarEmpleadoDTO;
import com.UTN.Seguridad.DTO.RegistrarEmpleadoDTO;
import com.UTN.Seguridad.Entidades.Empleado;
import com.UTN.Seguridad.Excepciones.EmpleadoExistenteException;
import com.UTN.Seguridad.Servicios.EmpleadoService;
import com.UTN.Seguridad.Servicios.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl>{

    @GetMapping("/verDatosEmpleado")
    public ResponseEntity<?> verDatosEmpleado(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/modificarDatosEmpleado")
    public ResponseEntity<?> modificarDatosEmpleado(@RequestBody ModificarEmpleadoDTO modificarEmpleadoDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(modificarEmpleadoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    //  @GetMapping("/actualizarContraseña")
    //public ResponseEntity<?> actualizarContrasena(EmpleadoDTO empleadoDTO){
    //  try {
    //    return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarContrasena(empleadoDTO));
    //} catch (Exception e) {
    //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    // }
    // }

    @Autowired
    private EmpleadoService empleadoService;
    @PostMapping("/registrarEmpleado") //Puede que haya q eliminar este metodo
    public ResponseEntity<?> registrarEmpleado(@RequestBody RegistrarEmpleadoDTO registrarEmpleadoDTO) {
        try {
            Empleado empleado = empleadoService.registrarEmpleado(registrarEmpleadoDTO);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoExistenteException e) {
            // Maneja el caso de un empleado duplicado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un empleado con el mismo correo electrónico: " + e.getMessage());
        } catch (Exception e) {
            // Maneja otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el empleado: " + e.getMessage());
        }
    }

}