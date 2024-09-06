package persistencia;

import java.util.List;
import model.Chale;

public interface ChaleDAO {
	public String inserir(Chale cha);
	public String alterar(Chale cha);
	public String excluir(Chale cha);
	public List<Chale> listarTodos();
	public Chale pesquisarPorCod(Integer codChale);
}
