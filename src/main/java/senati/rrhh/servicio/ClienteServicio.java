package senati.rrhh.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.modelo.Cliente;
import senati.rrhh.repositorio.ClienteRepositorio;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{
    @Autowired
    public ClienteRepositorio clienteRepositorio;

    //Listar clientes
    @Override
    public List<Cliente> ListarClientes() {
        return clienteRepositorio.findAll();
    }

    //Buscar clientes
    @Override
    public Cliente buscarclientePorId(Integer idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    //Guardar clientes
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    //Eliminar clientes
    @Override
    public void eliminarcliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
