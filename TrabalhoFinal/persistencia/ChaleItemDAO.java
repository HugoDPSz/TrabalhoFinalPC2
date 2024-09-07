package persistencia;

import java.util.List;
import model.ChaleItem;

public interface ChaleItemDAO {
    String inserir(ChaleItem chaleItem);
    String alterar(ChaleItem chaleItem);
    String excluir(int codChale, String nomeItem);
    List<ChaleItem> listarTodos();
    ChaleItem pesquisarPorCodItem(int codChale, String nomeItem);


}
