package persistencia;

import model.Item;

import java.util.List;

public interface ItemDAO {
    String inserir(Item item);
    String excluir(String nomeItem);
    Item buscarPorNome(String nomeItem);
    List<Item> listarTodos();
}
