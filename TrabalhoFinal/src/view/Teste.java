package view;

import java.sql.Connection;
import java.sql.Date;
import model.*;
import persistencia.*;

public class Teste {
	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnection();
		if (con != null) {
			System.out.println("Conexão estabelecida com sucesso.");
			ConnectionFactory.close(con);
		} else {
			System.out.println("Falha ao estabelecer conexão.");
			return;
		}

		ChaleDAOImp chaleDAO = new ChaleDAOImp();
		ChaleItemDAOImp chaleItemDAO = new ChaleItemDAOImp();
		ClienteDAOImp clienteDAO = new ClienteDAOImp();
		HospedagemDAOImp hospedagemDAO = new HospedagemDAOImp();
		HospedagemServicoDAOImp hospedagemServicoDAO = new HospedagemServicoDAOImp();
		ItemDAOImp itemDAO = new ItemDAOImp();
		ServicoDAOImp servicoDAO = new ServicoDAOImp();
		TelefoneDAOImp telefoneDAO = new TelefoneDAOImp();

		Cliente cliente = new Cliente();
		cliente.setCodCliente(1);
		cliente.setNomeCliente("John Doe");
		cliente.setEnderecoCliente("123 Main St");
		cliente.setBairroCliente("Centro");
		cliente.setCidadeCliente("Cidade Exemplo");
		cliente.setEstadoCliente("SP");
		cliente.setCepCliente("12345-678");
		cliente.setNascimentoCliente(Date.valueOf("1990-01-01"));
		cliente.setRgCliente("123456789");
		System.out.println(clienteDAO.inserir(cliente));

		Chale chale = new Chale();
		chale.setCodChale(1);
		chale.setLocalizacao("Zona Sul");
		chale.setCapacidade(4);
		chale.setValorAltaEstacao(1000.0);
		chale.setValorBaixaEstacao(500.0);
		System.out.println(chaleDAO.inserir(chale));

		Item item = new Item();
		item.setNomeItem("Toalha");
		item.setDescricaoItem("Toalha de banho com logo");
		System.out.println(itemDAO.inserir(item));

		Servico servico = new Servico();
		servico.setCodServico(1);
		servico.setNomeServico("Limpeza");
		servico.setValorServico(50);
		System.out.println(servicoDAO.inserir(servico));

		Telefone telefone = new Telefone();
		telefone.setTelefone("987654321");
		telefone.setCodCliente(1);
		telefone.setTipoTelefone("Celular");
		telefoneDAO.inserir(telefone);

		Hospedagem hospedagem = new Hospedagem();
		hospedagem.setCodHospedagem(1);
		hospedagem.setCodChale(1);
		hospedagem.setCodCliente(1);
		hospedagem.setDataInicio(Date.valueOf("2024-09-01"));
		hospedagem.setDataFim(Date.valueOf("2024-09-10"));
		hospedagem.setEstado("Ativa");
		hospedagem.setQtdPessoas(2);
		hospedagem.setDesconto(10.0);
		hospedagem.setValorFinal(900.0);
		System.out.println(hospedagemDAO.inserir(hospedagem));

		HospedagemServico hospedagemServico = new HospedagemServico();
		hospedagemServico.setCodHospedagem(1);
		hospedagemServico.setDataServico(java.time.LocalDate.now());
		hospedagemServico.setCodServico(1);
		hospedagemServico.setValorServico(50); // Valor do serviço
		System.out.println(hospedagemServicoDAO.inserir(hospedagemServico));

		// Criando e inserindo um ChaleItem
		ChaleItem chaleItem = new ChaleItem();
		chaleItem.setCodChale(1);
		chaleItem.setNomeItem("Toalha");
		System.out.println(chaleItemDAO.inserir(chaleItem));
		System.out.println("---------------------------------------");
		// Listando todos os Clientes
		System.out.println("Clientes:");
		for (Cliente c : clienteDAO.listarTodos()) {
			System.out.println(c.getNomeCliente() + " - " + c.getEnderecoCliente());
		}
		System.out.println("---------------------------------------");
		System.out.println("Chales:");
		for (Chale ch : chaleDAO.listarTodos()) {
			System.out.println(ch.getCodChale() + " - " + ch.getLocalizacao());
		}
		System.out.println("---------------------------------------");
		System.out.println("Itens:");
		for (Item i : itemDAO.listarTodos()) {
			System.out.println(i.getNomeItem() + " - " + i.getDescricaoItem());
		}
		System.out.println("---------------------------------------");
		System.out.println("Serviços:");
		for (Servico s : servicoDAO.listarTodos()) {
			System.out.println(s.getCodServico() + " - " + s.getNomeServico());
		}
		System.out.println("---------------------------------------");
		System.out.println("Telefones:");
		for (Telefone t : telefoneDAO.listarTodos()) {
			System.out.println(t.getTelefone() + " - " + t.getTipoTelefone());
		}
		System.out.println("---------------------------------------");
		System.out.println("Hospedagens:");
		for (Hospedagem h : hospedagemDAO.listarTodos()) {
			System.out.println(h.getCodHospedagem() + " - " + h.getEstado());
		}
		System.out.println("---------------------------------------");
		System.out.println("Hospedagem Serviços:");
		for (HospedagemServico hs : hospedagemServicoDAO.listarTodos()) {
			System.out.println(hs.getCodHospedagem() + " - " + hs.getCodServico() + " - " + hs.getDataServico());
		}

		System.out.println("---------------------------------------");
		System.out.println("Chale Items:");
		for (ChaleItem ci : chaleItemDAO.listarTodos()) {
			System.out.println(ci.getCodChale() + " - " + ci.getNomeItem());
		}

		System.out.println("---------------------------------------");


		try {
			itemDAO.excluir(item.getNomeItem());
			System.out.println("Item excluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir item: " + e.getMessage());
		}

		System.out.println("Items:");
		for (ChaleItem ci : chaleItemDAO.listarTodos()) {
			System.out.println(ci.getCodChale() + " - " + ci.getNomeItem());
		}

		try {
			hospedagemDAO.excluir(hospedagem);
			System.out.println("Hospedagem excluída com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir hospedagem: " + e.getMessage());
		}

		System.out.println("Hospedagens:");
		for (Hospedagem h : hospedagemDAO.listarTodos()) {
			System.out.println(h.getCodHospedagem() + " - " + h.getEstado());
		}

		try {
			clienteDAO.excluir(cliente);
			System.out.println("Cliente excluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir cliente: " + e.getMessage());
		}

		System.out.println("Clientes:");
		for (Cliente c : clienteDAO.listarTodos()) {
			System.out.println(c.getNomeCliente() + " - " + c.getEnderecoCliente());
		}

		try {
			chaleDAO.excluir(chale.getCodChale());
			System.out.println("Chale excluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir chale: " + e.getMessage());
		}

		System.out.println("Chales:");
		for (Chale ch : chaleDAO.listarTodos()) {
			System.out.println(ch.getCodChale() + " - " + ch.getLocalizacao());
		}


		try {
			servicoDAO.excluir(servico);
			System.out.println("Servico excluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir servico: " + e.getMessage());
		}

		System.out.println("Serviços:");
		for (Servico s : servicoDAO.listarTodos()) {
			System.out.println(s.getCodServico() + " - " + s.getNomeServico());
		}

		try {
			telefoneDAO.excluir(telefone.getTelefone());
			System.out.println("Telefone excluído com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir telefone: " + e.getMessage());
		}

	}
}
