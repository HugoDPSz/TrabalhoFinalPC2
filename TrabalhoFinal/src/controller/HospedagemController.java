package controller;

import java.util.List;
import model.Hospedagem;
import persistencia.HospedagemDAOImp;

public class HospedagemController {
	public String inserir(Hospedagem hos) {
		HospedagemDAOImp dao = new HospedagemDAOImp();
		return dao.inserir(hos);
	}
	
	public String alterar(Hospedagem hos) {
		HospedagemDAOImp dao = new HospedagemDAOImp();
		return dao.alterar(hos);
	}
	
	public String excluir(Integer codHospedagem) {
		HospedagemDAOImp dao = new HospedagemDAOImp();
		return dao.excluir(codHospedagem);
	}
	
	public List<Hospedagem> listarTodos(Hospedagem hos){
		HospedagemDAOImp dao = new HospedagemDAOImp();
		return dao.listarTodos();
	}
	
	public Hospedagem pesquisarPorCod(Integer cod) {
		HospedagemDAOImp dao = new HospedagemDAOImp();
		return dao.pesquisarPorCod(cod);
	}
}
