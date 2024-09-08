package persistencia;

import model.Telefone;
import java.util.List;

public interface TelefoneDAO {
    void inserir(Telefone telefone);
    void alterar(Telefone telefone);
    void excluir(String telefone);
    Telefone buscarPorNumero(String telefone);
    List<Telefone> listarTodos();
}
