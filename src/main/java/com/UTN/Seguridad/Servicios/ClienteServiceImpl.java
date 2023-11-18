package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.DTO.ClienteDTO;
import com.UTN.Seguridad.DTO.ModificarClienteDTO;
import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Entidades.Usuario;
import com.UTN.Seguridad.Repositorios.BaseRepository;
import com.UTN.Seguridad.Repositorios.ClienteRepository;
import com.UTN.Seguridad.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long>
implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository) {
        super(baseRepository);
    }
    @Override
    @Transactional

    public List<Cliente> mostrarClientes() throws  Exception{
        try {

            List<Cliente> clientes = clienteRepository.findAll();
            return clientes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Cliente modificarCliente(ModificarClienteDTO clienteDTO) throws Exception{
        try {
            // buscamos al cliente

            Optional<Cliente> cliente = clienteRepository.findById(clienteDTO.getIdCliente());
            if (cliente.isEmpty()) throw new Exception("no se encontro el cliente");
            Cliente entityUpdate = new Cliente();
            entityUpdate.setApellido(clienteDTO.getApellido());
            entityUpdate.setEmail(clienteDTO.getEmail());
            entityUpdate.setNombre(clienteDTO.getNombre());
            entityUpdate.setTelefono(clienteDTO.getTelefono());
            entityUpdate.setFecha_modificacion(new Date());
            entityUpdate.setId(clienteDTO.getIdCliente());
            //los domicilios se editarán a parte
            clienteRepository.save(entityUpdate);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ClienteDTO datosCliente(Long id) throws Exception{
        try{
            Cliente cliente = clienteRepository.buscarPorId(id);
            ClienteDTO clienteDTO= new ClienteDTO();

            clienteDTO.setIdCliente(cliente.getId());
            clienteDTO.setNombre(cliente.getNombre());
            clienteDTO.setApellido(cliente.getApellido());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setDomicilio(cliente.getDomicilios());
            //clienteDTO.setContrasena(cliente.getUsuario().getPassword());

            return clienteDTO;
         }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public Cliente modificardatos(ClienteDTO clienteDto) throws Exception{
        try{
            Cliente cliente = clienteRepository.buscarPorId(clienteDto.getIdCliente());

            if(clienteDto.getEmail() != null && !clienteDto.getEmail().isEmpty())
            cliente.setEmail(clienteDto.getEmail());

            if(clienteDto.getTelefono() != null && !clienteDto.getTelefono().isEmpty())
                cliente.setTelefono(clienteDto.getTelefono());

            List<Domicilio> domiciliosClientes = cliente.getDomicilios();
            List<Domicilio> domiciliosDTO = clienteDto.getDomicilio();
            for(Domicilio domicilio : domiciliosDTO){
                if(!domiciliosClientes.contains(domicilio)){
                    domiciliosClientes.add(domicilio);
                }
            }


            Usuario usuarioCliente = usuarioRepository.buscarPorId(clienteDto.getIdCliente());

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

            for (int i = 0; i < usuarioCliente.getPassword().length(); i++) {
                char c = usuarioCliente.getPassword().charAt(i);
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

            if (clienteDto.getContrasena().length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
                usuarioCliente.setPassword(clienteDto.getContrasena());

            } else {
                throw new Exception("la contraseña no tiene los requisitos adecuados");
            }

            clienteRepository.save(cliente);
            return cliente;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }



}

