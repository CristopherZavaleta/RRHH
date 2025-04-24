package senati.rrhh.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.modelo.Empleado;
import senati.rrhh.repositorio.EmpleadoRepositorio;

import java.util.List;

@Service
public class EmpleadoServicio  implements IEmpleadoServicio{
    @Autowired
    public EmpleadoRepositorio empleadoRepositorio;

    //Listar empleados
    @Override
    public List<Empleado> ListarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    //Buscar empleado
    @Override
    public Empleado buscarempleadoPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    //Guardar empleado
    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    //Eliminar empleado
    @Override
    public void eliminarempleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}
