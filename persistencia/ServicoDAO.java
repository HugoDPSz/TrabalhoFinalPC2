package persistencia;

import model.Servico;

import java.util.List;

public interface ServicoDAO {
    public String inserir(Servico servico);
    public String alterar(Servico servico);
    public String excluir(Servico servico);
    public List<Servico> listarTodos();
    Servico pesquisarPorCod(Integer codServico);
}
