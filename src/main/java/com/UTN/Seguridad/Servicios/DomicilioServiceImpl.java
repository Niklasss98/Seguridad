package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.DTO.ClienteDomicilioDTO;
import com.UTN.Seguridad.Entidades.Cliente;
import com.UTN.Seguridad.Entidades.Domicilio;
import com.UTN.Seguridad.Repositorios.BaseRepository;
import com.UTN.Seguridad.Repositorios.DomicilioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, Long> implements DomicilioService {

    @Autowired private DomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional

    public List<Domicilio> mostrarDomiciliosCliente(Cliente cliente) throws  Exception{
        try {

            List<Domicilio> domicilios = domicilioRepository.findAll();
            return domicilios;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Domicilio modificarDomicilioCliente(ClienteDomicilioDTO domicilioDTO) throws Exception{
        try {
            // buscamos al cliente

            Optional<Domicilio> domicilio = domicilioRepository.findById(domicilioDTO.getId());
            if (domicilio.isEmpty()) throw new Exception("no se encontro el domicilio");
            Domicilio entityUpdate = new Domicilio();
            entityUpdate.setCalle(domicilioDTO.getCalle());
            entityUpdate.setLocalidad(domicilioDTO.getLocalidad());
            entityUpdate.setNumero(domicilioDTO.getNumero());
            entityUpdate.setFecha_modificacion(new Date());
            //los domicilios se editarán a parte
            domicilioRepository.save(entityUpdate);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
