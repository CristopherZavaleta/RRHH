package senati.rrhh.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senati.rrhh.modelo.Cliente;
import senati.rrhh.modelo.Empleado;
import senati.rrhh.servicio.IClienteServicio;
import senati.rrhh.servicio.IEmpleadoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/rrhh-app

@RequestMapping("rrhh-app")
@CrossOrigin(value = "http://localhost:300")

public class ClienteControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ClienteControlador.class);
    @Autowired
    private IClienteServicio clienteServicio;

    //https://localhost:8080/rrhh-app/clientes

    //Listar clientes.
    @GetMapping("/clientes")
    public List<Cliente> obtenerclientes(){
        var clientes = clienteServicio.ListarClientes();
        clientes.forEach(cliente -> logger.info(cliente.toString()));
        return clientes;
    }

    //Registrar clientes.
    @PostMapping("/clientes")
    public Cliente agregarclientes(@RequestBody Cliente cliente) {
        return clienteServicio.guardarCliente(cliente);
    }

    //Busca clientes.
    @GetMapping("/clientes{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable Integer id) {
        Cliente cliente = clienteServicio.buscarclientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    //Editar clientes.
    @PutMapping("clientes/{id}")
    public ResponseEntity<Cliente> actualizarEmpleado(@PathVariable Integer id,
                                                       @RequestBody Cliente clienteUpdate){
        Cliente cliente = clienteServicio.buscarclientePorId(id);
        cliente.setApellido(clienteUpdate.getApellido());
        cliente.setNombre(clienteUpdate.getNombre());
        cliente.setTelefono(clienteUpdate.getTelefono());
        cliente.setDireccio(clienteUpdate.getDireccio());
        cliente.setEmail(clienteUpdate.getEmail());

        clienteServicio.guardarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    //Eliminar clientes.
    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarclientePorId(id);
        clienteServicio.eliminarcliente(cliente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
