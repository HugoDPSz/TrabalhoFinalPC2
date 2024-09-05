package controller;

import java.util.List;
import model.Cliente;
import persistencia.ClienteDAOImp;

public class ClienteController {

    public String inserir(Cliente cliente) {
        ClienteDAOImp dao = new ClienteDAOImp();
        return dao.inserir(cliente);
    }

    public String alterar(Cliente cliente) {
        ClienteDAOImp dao = new ClienteDAOImp();
        return dao.alterar(cliente);
    }

    public String excluir(Cliente cliente) {
        ClienteDAOImp dao = new ClienteDAOImp();
        return dao.excluir(cliente);
    }

    public List<Cliente> listarTodos() {
        ClienteDAOImp dao = new ClienteDAOImp();
        return dao.listarTodos();
    }
}
