package view;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import model.*;
import persistencia.*;
import persistencia.ConnectionFactory;
import persistencia.HospedagemDAOImp;

public class Teste {
	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnection();
		if (con != null) {
			System.out.println("Conexão estabelecida com sucesso.");
			ConnectionFactory.close(con);
		} else {
			System.out.println("Falha ao estabelecer conexão.");
			return; // Se a conexão falhar, encerramos o programa
		}

		// Testando ChaleDAO
		ChaleDAOImp chaleDAO = new ChaleDAOImp();
		Chale chale = new Chale();
		chale.setCodChale(1);
		chale.setLocalizacao("Montanha");
		chale.setCapacidade(4);
		chale.setValorAltaEstacao(500.0);
		chale.setValorBaixaEstacao(300.0);

		// Inserir chalé
		System.out.println("Inserir chalé: " + chaleDAO.inserir(chale));

		// Listar todos os chalés
		List<Chale> chales = chaleDAO.listarTodos();
		if (chales != null) {
			for (Chale c : chales) {
				System.out.println(c);
			}
		} else {
			System.out.println("Nenhum chalé encontrado.");
		}

		// Testando ClienteDAO
		ClienteDAOImp clienteDAO = new ClienteDAOImp();
		Cliente cliente = new Cliente();
		cliente.setCodCliente(1);
		cliente.setNomeCliente("João Silva");
		cliente.setRgCliente("123456789");
		cliente.setBairroCliente("Centro");
		cliente.setCidadeCliente("São Paulo");
		cliente.setCepCliente("01001-000");
		cliente.setNascimentoCliente(Date.valueOf("1990-01-01"));

		// Inserir cliente
		System.out.println("Inserir cliente: " + clienteDAO.inserir(cliente));

		// Listar todos os clientes
		List<Cliente> clientes = clienteDAO.listarTodos();
		if (clientes != null) {
			for (Cliente cli : clientes) {
				System.out.println(cli);
			}
		} else {
			System.out.println("Nenhum cliente encontrado.");
		}

		// Testando HospedagemDAO (agora que já temos um cliente e um chalé)
		HospedagemDAOImp hospedagemDAO = new HospedagemDAOImp();
		Hospedagem hospedagem = new Hospedagem();
		hospedagem.setCodHospedagem(1);
		hospedagem.setCodChale(1);  // Chalé inserido anteriormente
		hospedagem.setCodCliente(1);  // Cliente inserido anteriormente
		hospedagem.setEstado("Confirmada");
		hospedagem.setDataInicio(Date.valueOf("2024-12-01"));
		hospedagem.setDataFim(Date.valueOf("2024-12-10"));
		hospedagem.setQtdPessoas(3);
		hospedagem.setDesconto(10.0);
		hospedagem.setValorFinal(2700.0);

		// Inserir hospedagem
		System.out.println("Inserir hospedagem: " + hospedagemDAO.inserir(hospedagem));

		// Alterar hospedagem
		hospedagem.setQtdPessoas(4);
		System.out.println("Alterar hospedagem: " + hospedagemDAO.alterar(hospedagem));

		// Listar todas as hospedagens
		List<Hospedagem> hospedagens = hospedagemDAO.listarTodos();
		if (hospedagens != null) {
			System.out.println("Listar todas as hospedagens:");
			for (Hospedagem hos : hospedagens) {
				System.out.println(hos);
			}
		} else {
			System.out.println("Nenhuma hospedagem encontrada.");
		}

		// Pesquisar hospedagem por código
		Hospedagem hospedagemEncontrada = hospedagemDAO.pesquisarPorCod(1);
		System.out.println("Hospedagem encontrada: " + hospedagemEncontrada);

		// Excluir hospedagem
		System.out.println("Excluir hospedagem: " + hospedagemDAO.excluir(hospedagem));

		// Excluir cliente e chalé após a hospedagem
		System.out.println("Excluir cliente: " + clienteDAO.excluir(cliente));
		System.out.println("Excluir chalé: " + chaleDAO.excluir(chale));
	}
}
