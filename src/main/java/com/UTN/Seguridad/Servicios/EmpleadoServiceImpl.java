package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.DTO.ModificarEmpleadoDTO;
import com.UTN.Seguridad.DTO.RegistrarEmpleadoDTO;
import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Entidades.Empleado;
import com.UTN.Seguridad.Entidades.Usuario;
import com.UTN.Seguridad.Excepciones.ContraseñaInvalidaException;
import com.UTN.Seguridad.Excepciones.EmpleadoExistenteException;
import com.UTN.Seguridad.Repositorios.BaseRepository;
import com.UTN.Seguridad.Repositorios.EmpleadoRepository;
import com.UTN.Seguridad.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, Long> implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmpleadoServiceImpl(BaseRepository<Empleado, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public RegistrarEmpleadoDTO datosEmpleado(Long id) throws Exception{
        try{
            Empleado empleado = empleadoRepository.buscarPorId(id);
            RegistrarEmpleadoDTO registrarEmpleadoDTO = new RegistrarEmpleadoDTO();

            registrarEmpleadoDTO.setNombre(empleado.getNombre());
            registrarEmpleadoDTO.setApellido(empleado.getApellido());
            registrarEmpleadoDTO.setEmail(empleado.getEmail());
            registrarEmpleadoDTO.setTelefono(empleado.getTelefono());
            registrarEmpleadoDTO.setDomicilio(empleado.getDomicilios());
            registrarEmpleadoDTO.setContrasena(empleado.getUsuario().getPassword());

            return registrarEmpleadoDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public Empleado modificardatos(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception{
        try{
            Empleado empleado = empleadoRepository.buscarPorId(modificarEmpleadoDTO.getIdEmpleado());

            if(modificarEmpleadoDTO.getEmail() != null && !modificarEmpleadoDTO.getEmail().isEmpty())
                empleado.setEmail(modificarEmpleadoDTO.getEmail());

            if(modificarEmpleadoDTO.getTelefono() != null && !modificarEmpleadoDTO.getTelefono().isEmpty())
                empleado.setTelefono(modificarEmpleadoDTO.getTelefono());

            List<Domicilio> domiciliosEmpleados = empleado.getDomicilios();
            List<Domicilio> domiciliosDTO = modificarEmpleadoDTO.getDomicilio();
            for(Domicilio domicilio : domiciliosDTO){
                if(!domiciliosEmpleados.contains(domicilio)){
                    domiciliosEmpleados.add(domicilio);
                }
            }


            Usuario usuarioEmpleado = usuarioRepository.buscarPorId(modificarEmpleadoDTO.getIdEmpleado());

            //DATOS PARA LA VERIFICACION DE CONTRASEÑA
            final int MAX=8;
            final int MIN_Uppercase = 1;
            final int MIN_Lowercase = 1;
            final int NUM_Digits = 1;
            final int Special = 1;
            int uppercaseCounter = 0;
            int lowercaseCounter = 0;
            int digitCounter = 0;
            int specialCounter = 0;

            for (int i = 0; i < usuarioEmpleado.getPassword().length(); i++) {
                char c = usuarioEmpleado.getPassword().charAt(i);
                if (Character.isUpperCase(c))
                    uppercaseCounter++;
                else if (Character.isLowerCase(c))
                    lowercaseCounter++;
                else if (Character.isDigit(c))
                    digitCounter++;
                //revisar
                if (c >= 33 && c <= 46 || c == 64) {
                    specialCounter++;
                }
            }

            if (modificarEmpleadoDTO.getContrasena().length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
                usuarioEmpleado.setPassword(modificarEmpleadoDTO.getContrasena());

            } else {
                throw new Exception("la contraseña no tiene los requisitos adecuados");
            }

            empleadoRepository.save(empleado);
            return empleado;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    //Metodo para registrarEmpleado y verificar anteriormente que no exista un empleado con ese mail

    @Override
    @Transactional
    public Empleado registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO) throws Exception {
        try{
            //Verifico si ya existe un empleado con el mail ingresado
            Empleado empleadoExistente = empleadoRepository.buscarPorEmail(registrarEmpleadoDTO.getEmail());
            if (empleadoExistente != null){
                throw new EmpleadoExistenteException("Ya existe un empleado con el mismo mail");
            }

            if (!validarContraseña(registrarEmpleadoDTO.getContrasena())) {
                throw new ContraseñaInvalidaException("La contraseña no cumple con los requisitos mínimos.");
            }
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre(registrarEmpleadoDTO.getNombre());
            nuevoEmpleado.setEmail(registrarEmpleadoDTO.getEmail());
            nuevoEmpleado.setApellido(registrarEmpleadoDTO.getApellido());
            nuevoEmpleado.setTelefono(registrarEmpleadoDTO.getTelefono());
            nuevoEmpleado.setDomicilios(registrarEmpleadoDTO.getDomicilio());

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(nuevoEmpleado.getNombre());
            nuevoUsuario.setPassword(registrarEmpleadoDTO.getContrasena());
            nuevoUsuario.setRole(registrarEmpleadoDTO.getRol());

            nuevoEmpleado.setUsuario(nuevoUsuario);
            empleadoRepository.save(nuevoEmpleado);
            return nuevoEmpleado;
        } catch (Exception e){
            throw new RuntimeException("error al registrar el empleado" + e.getMessage());
        }
    }
    private boolean validarContraseña(String contraseña) {
        if (contraseña.length() < 8) {
            return false; // La contraseña no tiene al menos 8 caracteres
        }

        boolean contieneMayuscula = false;
        boolean contieneMinuscula = false;
        boolean contieneSimbolo = false;

        for (char caracter : contraseña.toCharArray()) {
            if (Character.isUpperCase(caracter)) {
                contieneMayuscula = true;
            } else if (Character.isLowerCase(caracter)) {
                contieneMinuscula = true;
            } else if ("!@#$%^&*".indexOf(caracter) >= 0) {
                contieneSimbolo = true;
            }
        }

        return contieneMayuscula && contieneMinuscula && contieneSimbolo;
    }



}
