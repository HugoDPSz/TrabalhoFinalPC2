package persistencia;

import model.HospedagemServico;

import java.util.List;

public interface HospedagemServicoDAO {
    public String inserir(HospedagemServico hospedagemServico);
    public String alterar(HospedagemServico hospedagemServico);
    public String excluir(HospedagemServico hospedagemServico);
    public List<HospedagemServico> listarTodos();
    public HospedagemServico pesquisarPorCod(Integer codHospedagem, Integer codServico);
}
