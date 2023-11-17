package com.UTN.Seguridad.Servicios;


import com.UTN.Seguridad.DTO.ModificarEmpleadoDTO;
import com.UTN.Seguridad.DTO.RegistrarEmpleadoDTO;
import com.UTN.Seguridad.Entidades.Empleado;

public interface EmpleadoService extends BaseService<Empleado, Long>{
    public RegistrarEmpleadoDTO datosEmpleado(Long id) throws Exception;

    public Empleado modificardatos(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception;

    // public Empleado actualizarContrasena(EmpleadoDTO empleadoDto) throws Exception;
    public Empleado registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO) throws Exception;
}
