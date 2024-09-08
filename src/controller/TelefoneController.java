package controller;

import java.util.List;
import model.Telefone;
import persistencia.TelefoneDAO;
import persistencia.TelefoneDAOImp;

public class TelefoneController {

    private TelefoneDAO dao;

    public TelefoneController() {
        this.dao = new TelefoneDAOImp();
    }

    public void inserir(Telefone telefone) {
        dao.inserir(telefone);
    }

    public void alterar(Telefone telefone) {
        dao.alterar(telefone);
    }

    public void excluir(String numero) {
        dao.excluir(numero);
    }

    public List<Telefone> listarTodos() {
        return dao.listarTodos();
    }

    public Telefone buscarPorNumero(String numero) {
        return dao.buscarPorNumero(numero);
    }
}

