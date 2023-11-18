package com.UTN.Seguridad.Entidades;



import com.UTN.Seguridad.Enumeraciones.RolUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="usuario", uniqueConstraints ={@UniqueConstraint(columnNames = {"username"})}) // IMPORTANTE: Es un constraint para que no se repitan los usernames en la base de datos
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
//@SQLDelete(sql = "UPDATE usuario SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
//@Where(clause = "deleted=false")
public class Usuario extends  BaseEntidad implements UserDetails {

    /*
    * IMPORTANTISIMO: Usuario deberia extenderse tanto a Empleado como a Cliente
    * Aca se modela la entidad que va a matchear con todos los datos q se carguen en la pagina
    * para registrarse
    *
    * Si les pinta a los que estan laburando aca Eliminar Cliente y Empleado, usar solamente una entidad
    * y elegirle un rolUsuario para diferenciar
    *
    * Si no como puse recien, extender esta clase a ambos y luego cargar los datos necesarios de cada uno,
    * pero habria q ver la diferencia entre ambos y como se conecta la pagina con el controller y el controller a cada service correspondiente
    * para probar con las entidades
    *
    *
    */

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private RolUsuario role;


    //Puede q haya q cambiar los return de cada funcion
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); //puede q no se name
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*
    //Relacion one to one con usuario (foreign key usuario)
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    */

}
