package controller;

import java.util.List;
import model.Item;
import persistencia.ItemDAO;
import persistencia.ItemDAOImp;

public class ItemController {
    private ItemDAO itemDAO;

    public ItemController() {
        this.itemDAO = new ItemDAOImp();
    }

    public String inserir(Item item) {
        return itemDAO.inserir(item);
    }

    public String excluir(String nomeItem) {
        return itemDAO.excluir(nomeItem);
    }

    public Item buscarPorNome(String nomeItem) {
        return itemDAO.buscarPorNome(nomeItem);
    }

    public List<Item> listarTodos() {
        return itemDAO.listarTodos();
    }
}
