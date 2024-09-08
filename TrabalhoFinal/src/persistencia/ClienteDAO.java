package persistencia;

import java.util.List;
import model.Cliente;

public interface ClienteDAO {
	public String inserir(Cliente cli);
	public String alterar(Cliente cli);
	public String excluir(Integer codCliente);
	public List<Cliente> listarTodos();
	public Cliente pesquisarPorCod(Integer codCliente);
}
