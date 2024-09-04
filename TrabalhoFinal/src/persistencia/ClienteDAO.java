package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {
    String inserir(Cliente cliente);
    List<Cliente> read();
}
