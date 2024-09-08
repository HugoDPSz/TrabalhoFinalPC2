package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAOImp implements ClienteDAO {

	@Override
	public String inserir(Cliente cli) {
		String sql = "INSERT INTO Cliente(codCliente, nomeCliente, rgCliente, bairroCliente, cidadeCliente, estadoCliente, enderecoCliente, cepCliente, nascimentoCliente) VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cli.getCodCliente());
			pst.setString(2, cli.getNomeCliente());
			pst.setString(3, cli.getRgCliente());
			pst.setString(4, cli.getBairroCliente());
			pst.setString(5, cli.getCidadeCliente());
			pst.setString(6, cli.getEstadoCliente());
			pst.setString(7, cli.getEnderecoCliente());
			pst.setString(8, cli.getCepCliente());
			pst.setDate(9, new Date(cli.getNascimentoCliente().getTime()));

			int res = pst.executeUpdate();
			return res > 0 ? "Inserido com sucesso." : "Erro ao inserir.";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	@Override
	public String alterar(Cliente cli) {
		String sql = "UPDATE Cliente SET nomeCliente=?, rgCliente=?, bairroCliente=?, cidadeCliente=?, estadoCliente=?, enderecoCliente=?, cepCliente=?, nascimentoCliente=? WHERE codCliente=?";
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, cli.getNomeCliente());
			pst.setString(2, cli.getRgCliente());
			pst.setString(3, cli.getBairroCliente());
			pst.setString(4, cli.getCidadeCliente());
			pst.setString(5, cli.getEstadoCliente());
			pst.setString(6, cli.getEnderecoCliente());
			pst.setString(7, cli.getCepCliente());

			pst.setDate(8, new Date(cli.getNascimentoCliente().getTime()));
			pst.setInt(9, cli.getCodCliente());

			int res = pst.executeUpdate();
			return res > 0 ? "Alterado com sucesso." : "Erro ao alterar.";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	@Override
	public String excluir(Integer codCliente) {
		String sql = "DELETE FROM Cliente WHERE codCliente=?";
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, codCliente);
			int res = pst.executeUpdate();
			return res > 0 ? "Excluído com sucesso." : "Erro ao excluir.";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	@Override
	public List<Cliente> listarTodos() {
		String sql = "SELECT * FROM Cliente";
		List<Cliente> lista = new ArrayList<>();
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql);
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setCodCliente(rs.getInt("codCliente"));
				cli.setNomeCliente(rs.getString("nomeCliente"));
				cli.setRgCliente(rs.getString("rgCliente"));
				cli.setBairroCliente(rs.getString("bairroCliente"));
				cli.setCidadeCliente(rs.getString("cidadeCliente"));
				cli.setEstadoCliente(rs.getString("estadoCliente"));
				cli.setEnderecoCliente(rs.getString("enderecoCliente"));
				cli.setCepCliente(rs.getString("cepCliente"));
				cli.setNascimentoCliente(rs.getDate("nascimentoCliente"));

				lista.add(cli);
			}
			return lista;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Cliente pesquisarPorCod(Integer codCliente) {
		String sql = "SELECT * FROM Cliente WHERE codCliente=?";
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, codCliente);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Cliente cli = new Cliente();
				cli.setCodCliente(rs.getInt("codCliente"));
				cli.setNomeCliente(rs.getString("nomeCliente"));
				cli.setRgCliente(rs.getString("rgCliente"));
				cli.setBairroCliente(rs.getString("bairroCliente"));
				cli.setCidadeCliente(rs.getString("cidadeCliente"));
				cli.setEstadoCliente(rs.getString("estadoCliente"));
				cli.setEnderecoCliente(rs.getString("enderecoCliente"));
				cli.setCepCliente(rs.getString("cepCliente"));
				cli.setNascimentoCliente(rs.getDate("nascimentoCliente"));

				return cli;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
}
