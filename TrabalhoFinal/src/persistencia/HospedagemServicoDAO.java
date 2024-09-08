package persistencia;

import model.HospedagemServico;

import java.sql.Date;
import java.util.List;

public interface HospedagemServicoDAO {
    public String inserir(HospedagemServico hospedagemServico);
    public String alterar(HospedagemServico hospedagemServico);
    public String excluir(Integer codHospedagem, Date dataServico, Integer codServico);
    public List<HospedagemServico> listarTodos();
    public HospedagemServico pesquisarPorCod(Integer codHospedagem, Integer codServico);
}
