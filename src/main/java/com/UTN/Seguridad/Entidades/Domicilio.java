package com.UTN.Seguridad.Entidades;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="domicilio")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE domicilio SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")

public class Domicilio extends BaseEntidad{

    @Column(name = "calle",nullable = false)
    private String calle;

    @Column(name = "numero",nullable = false)
    private String numero;

    @Column(name = "localidad",nullable = false)
    private String localidad;

    @JsonBackReference(value = "cliente-domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    // DESPUES NOS FIJAMOS COMO FUNCIONA PERO ESTO RSSIRIVEW
    // @JsonBackReference
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonBackReference(value = "empleado-domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonBackReference
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

   // @OneToMany(orphanRemoval = true)                            //Relacion con Pedido
    //@JoinColumn(name = "domicilio_id")
   // private List<Pedido> pedidos = new ArrayList<>();

}

