package controller;

import java.util.List;
import model.ChaleItem;
import persistencia.ChaleItemDAO;
import persistencia.ChaleItemDAOImp;

public class ChaleItemController {

    private ChaleItemDAO dao;

    public ChaleItemController() {
        this.dao = new ChaleItemDAOImp();
    }

    public String inserir(ChaleItem chaleItem) {
        return dao.inserir(chaleItem);
    }

    public String alterar(ChaleItem chaleItem) {
        return dao.alterar(chaleItem);
    }

    public String excluir(ChaleItem chaleItem) {
        return dao.excluir(chaleItem);
    }

    public List<ChaleItem> listarTodos() {
        return dao.listarTodos();
    }

    public ChaleItem pesquisarPorCod(int codChale, String nomeItem) {
        return dao.pesquisarPorCodItem(codChale, nomeItem);
    }
}
