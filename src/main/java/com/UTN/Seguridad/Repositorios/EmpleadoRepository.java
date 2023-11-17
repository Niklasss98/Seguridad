package com.UTN.Seguridad.Repositorios;


import com.UTN.Seguridad.Entidades.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends BaseRepository<Empleado, Long>{
    @Query("SELECT c FROM Empleado c WHERE c.id = :id")
    Empleado buscarPorId(@Param("id") Long id);

    @Query ("SELECT c FROM Empleado c WHERE c.nombre = :nombre")
    List<Empleado> buscarPornombre(@Param("nombre") String nombre);


    @Query("SELECT c FROM Empleado c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    List<Empleado> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Query ("SELECT c FROM Empleado c WHERE c.nombre = :nombre")
    Page<Empleado> buscarPornombre(@Param("nombre") String nombre, Pageable pageable);

    @Query ("SELECT c FROM Empleado c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    Page<Empleado> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido, Pageable pageable);

    @Query ("SELECT c FROM Empleado c WHERE c.email = :email")
    Empleado buscarPorEmail(@Param("email") String email);


}
