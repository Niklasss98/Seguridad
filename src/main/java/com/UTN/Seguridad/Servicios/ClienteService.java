package com.UTN.Seguridad.Servicios;



import com.UTN.Seguridad.DTO.ClienteDTO;
import com.UTN.Seguridad.DTO.ModificarClienteDTO;
import com.UTN.Seguridad.Entidades.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Long>{
    public List<Cliente> mostrarClientes() throws Exception;
    public Cliente modificarCliente(ModificarClienteDTO clienteDTO) throws Exception;

    public ClienteDTO datosCliente(Long id) throws Exception;

    public Cliente modificardatos(ClienteDTO clienteDto) throws Exception;



}
