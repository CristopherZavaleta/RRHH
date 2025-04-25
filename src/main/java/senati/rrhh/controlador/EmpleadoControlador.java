package senati.rrhh.controlador;

import lombok.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import senati.rrhh.modelo.Empleado;
import senati.rrhh.servicio.IEmpleadoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//http://localhost:8080/rrhh-app

@RequestMapping("rrhh-app")
@CrossOrigin(value = "http://localhost:300")

public class EmpleadoControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);
    @Autowired
    private IEmpleadoServicio empleadoServicio;

    //https://localhost:8080/rrhh-app/empleados

    //Listar empleado.
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoServicio.ListarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    //Registrar empleado.
    @PostMapping("/empleados")
    public Empleado agregarempleado(@RequestBody Empleado empleado) {
        return empleadoServicio.guardarEmpleado(empleado);
    }

    //Buscar empleado.
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarempleadoPorId(id);
        return ResponseEntity.ok(empleado);
    }

    //Editar empleado.
    @PutMapping("empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id,
                                                       @RequestBody Empleado empleadoUpdate){
        Empleado empleado = empleadoServicio.buscarempleadoPorId(id);
        empleado.setApellidos(empleadoUpdate.getApellidos());
        empleado.setNombres(empleadoUpdate.getNombres());
        empleado.setArea(empleadoUpdate.getArea());
        empleado.setEmail(empleadoUpdate.getEmail());
        empleado.setSueldo(empleadoUpdate.getSueldo());
        empleado.setDireccion(empleadoUpdate.getDireccion());
        empleado.setTelefono(empleadoUpdate.getTelefono());

        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    //Eliminar empleado.
    @DeleteMapping("empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarempleadoPorId(id);
        empleadoServicio.eliminarempleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
