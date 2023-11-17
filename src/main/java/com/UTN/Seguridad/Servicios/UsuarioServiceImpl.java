package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.Entidades.Usuario;
import com.UTN.Seguridad.Repositorios.BaseRepository;
import com.UTN.Seguridad.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }


}

