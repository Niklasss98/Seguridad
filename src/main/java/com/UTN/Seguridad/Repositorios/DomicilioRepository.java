package com.UTN.Seguridad.Repositorios;


import com.UTN.Seguridad.Entidades.Domicilio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends BaseRepository <Domicilio, Long> {
    @Query("SELECT d FROM Domicilio d WHERE d.id = :id")
    Domicilio buscarPorId(@Param("id") Long id);
    @Query("SELECT d FROM Domicilio d WHERE d.calle = :calle")
    List<Domicilio> buscarPorCalle(@Param("calle") String calle);

    @Query("SELECT d FROM Domicilio d WHERE d.calle = :calle")
    Page<Domicilio> buscarPorCalle(@Param("calle") String calle, Pageable pageable);

    @Query("SELECT d FROM Domicilio d WHERE d.localidad = :localidad")
    List<Domicilio> buscarPorLocalidad(@Param("localidad") String localidad);

    @Query("SELECT d FROM Domicilio d WHERE d.localidad = :localidad")
    Page<Domicilio> buscarPorLocalidad(@Param("localidad") String localidad, Pageable pageable);

    @Query("SELECT cliente FROM Cliente cliente")
    List<Domicilio> mostrarDomiciliosCliente(@Param("id") Long id);

    @Query("SELECT c FROM Cliente c WHERE c.id= :id")
    Domicilio modificarDomicilioCliente(@Param("id") Long id);

}

