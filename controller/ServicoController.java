package controller;

import java.util.List;
import model.Servico;
import persistencia.ServicoDAO;
import persistencia.ServicoDAOImp;

public class ServicoController {
    private ServicoDAO servicoDAO;

    public ServicoController() {
        this.servicoDAO = new ServicoDAOImp();
    }

    public String inserir(Servico servico) {
        return servicoDAO.inserir(servico);
    }

    public String alterar(Servico servico) {
        return servicoDAO.alterar(servico);
    }

    public String excluir(Servico servico) {
        return servicoDAO.excluir(servico);
    }

    public List<Servico> listarTodos() {
        return servicoDAO.listarTodos();
    }

    public Servico pesquisarPorCod(Integer cod) {
        return servicoDAO.pesquisarPorCod(cod);
    }
}

