package senati.rrhh.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senati.rrhh.modelo.Cliente;
import senati.rrhh.servicio.IClienteServicio;

import java.util.List;

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
    @GetMapping("/clientes")
    public List<Cliente> obtenerclientes(){
        var clientes = clienteServicio.ListarClientes();
        clientes.forEach(cliente -> logger.info(cliente.toString()));
        return clientes;
    }
}
