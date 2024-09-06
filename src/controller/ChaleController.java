package controller;

import java.util.List;

import model.Chale;
import persistencia.ChaleDAOImp;

public class ChaleController {
	public String inserir(Chale cha) {
		ChaleDAOImp dao = new ChaleDAOImp();
		return dao.inserir(cha);
	}
	
	public String alterar(Chale cha) {
		ChaleDAOImp dao = new ChaleDAOImp();
		return dao.alterar(cha);
	}
	
	public String excluir(Chale cha) {
		ChaleDAOImp dao = new ChaleDAOImp();
		return dao.excluir(cha);
	}
	
	public List<Chale> listarTodos(Chale cha){
		ChaleDAOImp dao = new ChaleDAOImp();
		return dao.listarTodos();
	}
	
	public Chale pesquisarPorCod(Integer cod) {
		ChaleDAOImp dao = new ChaleDAOImp();
		return dao.pesquisarPorCod(cod);
	}
}
