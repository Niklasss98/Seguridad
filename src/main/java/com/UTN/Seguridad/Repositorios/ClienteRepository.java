package com.UTN.Seguridad.Repositorios;



import com.UTN.Seguridad.Entidades.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long>{

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Cliente buscarPorId(@Param("id") Long id);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    List<Cliente> buscarPornombre(@Param("nombre") String nombre);


    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    List<Cliente> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    Page<Cliente> buscarPornombre(@Param("nombre") String nombre, Pageable pageable);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    Page<Cliente> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido, Pageable pageable);

    @Query ("SELECT c FROM Cliente c WHERE c.email = :email")
    Cliente buscarPorEmail(@Param("email") String email);

    @Query("SELECT cliente FROM Cliente cliente")
    List<Cliente> mostrarClientes();

    @Query("SELECT c FROM Cliente c WHERE c.id= :id")
    Cliente modificarCliente(@Param("id") Long id);
}
