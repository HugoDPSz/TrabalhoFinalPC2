package persistencia;

import java.util.List;
import model.Hospedagem;

public interface HospedagemDAO {
	public String inserir(Hospedagem hos);
	public String alterar(Hospedagem hos);
	public String excluir(Integer codHospedagem);
	public List<Hospedagem> listarTodos();
	public Hospedagem pesquisarPorCod(Integer Hospedagem);
}
