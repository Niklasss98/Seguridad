package com.UTN.Seguridad.Servicios;



import com.UTN.Seguridad.DTO.ClienteDomicilioDTO;
import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Entidades.Domicilio;

import java.util.List;

public interface DomicilioService extends BaseService<Domicilio, Long>{
    public List<Domicilio> mostrarDomiciliosCliente(Cliente cliente) throws Exception;
    public  Domicilio modificarDomicilioCliente(ClienteDomicilioDTO domicilioDTO) throws Exception;
}
