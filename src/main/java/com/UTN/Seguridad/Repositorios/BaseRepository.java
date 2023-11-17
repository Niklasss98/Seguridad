package com.UTN.Seguridad.Repositorios;


import com.UTN.Seguridad.Entidades.BaseEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <E extends BaseEntidad, Id extends Serializable> extends JpaRepository<E,Id> {


}
