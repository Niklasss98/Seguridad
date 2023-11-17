package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.Entidades.BaseEntidad;
import com.UTN.Seguridad.Repositorios.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends BaseEntidad, ID extends Serializable> implements BaseService<E, ID> {

    @Autowired                                                                                                 // Inyeccion de Dependencias (Esta en el Power)
    protected BaseRepository<E,ID> baseRepository;
    public BaseServiceImpl(BaseRepository<E,ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
   public List<E> findAll() throws Exception {
       try {
          List<E> entities = baseRepository.findAll();
          return entities;
        } catch (Exception e) {
           throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();                          // Nunca checkea con el isPresent() pero está en un try catch,
            entityUpdate = baseRepository.save(entity);                     // en teoría no debería joder (Pasa lo mismo en findById())
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if(baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional

    public Page<E> findALL(Pageable pageable) throws  Exception{
        try {
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
