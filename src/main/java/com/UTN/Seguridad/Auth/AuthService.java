package com.UTN.Seguridad.Auth;


import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Entidades.Empleado;
import com.UTN.Seguridad.Entidades.Usuario;
import com.UTN.Seguridad.JWT.JwtService;
import com.UTN.Seguridad.Repositorios.ClienteRepository;
import com.UTN.Seguridad.Repositorios.EmpleadoRepository;
import com.UTN.Seguridad.Repositorios.UsuarioRepository;
import com.UTN.Seguridad.Enumeraciones.RolUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())); //springsecurity
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse registerCliente(RegisterClienteRequest request) {

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RolUsuario.CLIENTE)
                .build();

        Cliente cliente = Cliente.builder()
                .email(request.getEmail())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .nombre(request.getNombre())
                //Faltan Domcilios
                .build();

        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();

    }

    public AuthResponse registerEmpleado(RegisterEmpleadoRequest request) {

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RolUsuario.EMPLEADO)
                .build();

        Empleado empleado = Empleado.builder()
                .usuario(usuario)                       //Relacion 1 a 1 con usuario, puede q no vaya
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .rolEmpleado(request.getRol())
                //Domicilios
                .build();

        empleado.setUsuario(usuario);
        empleadoRepository.save(empleado);


        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}

