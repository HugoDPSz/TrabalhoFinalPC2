package controller;

import model.HospedagemServico;
import persistencia.HospedagemServicoDAO;

import java.sql.Date;
import java.util.List;

public class HospedagemServicoController {
    private HospedagemServicoDAO dao;

    public String inserir(HospedagemServico hospedagemServico) {
        return dao.inserir(hospedagemServico);
    }

    public String alterar(HospedagemServico hospedagemServico) {
        return dao.alterar(hospedagemServico);
    }

    public String excluir(Integer codHospedagem, Date dataServico, Integer codServico) {
        return dao.excluir(codHospedagem, dataServico, codServico);
    }

    public List<HospedagemServico> listarTodos() {
        return dao.listarTodos();
    }

    public HospedagemServico pesquisarPorCodigo(Integer codHospedagem, Integer codServico) {
        return dao.pesquisarPorCod(codHospedagem, codServico);
    }
}

