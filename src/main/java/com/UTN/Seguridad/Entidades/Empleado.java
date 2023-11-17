package com.UTN.Seguridad.Entidades;

import com.UTN.Seguridad.Enumeraciones.RolEmpleado;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
@Entity
@Builder
@Table(name="empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE Empleado SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")

public class Empleado extends BaseEntidad{

        @Column(name = "nombre", nullable = false)
        private String nombre;

        @Column(name = "apellido", nullable = false)
        private String apellido;

        @Column(name = "telefono")
        private String telefono;

        @Column(name = "email",nullable = false)
        private String email;

        @Column(name = "Rol", nullable = false)
        private RolEmpleado rolEmpleado;

        @JsonManagedReference(value = "empleado-domicilio")
        @OneToMany(mappedBy = "empleado",cascade = CascadeType.PERSIST)
        private List<Domicilio> domicilios = new ArrayList<Domicilio>();


        //Relacion one to one con usuario (foreign key usuario)
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "usuario_id")
        private Usuario usuario;

    }
