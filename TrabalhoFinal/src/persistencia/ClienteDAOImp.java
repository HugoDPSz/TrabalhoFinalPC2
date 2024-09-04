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
		String sql = "insert into Hospedagem(codCliente,nomeCliente,rgCliente,bairroCliente,cidadeCliente,cepCliente,nascimentoCliente)values (?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cli.getCodCliente());
			pst.setString(2, cli.getNomeCliente());
			pst.setString(3, cli.getRgCliente());
			pst.setString(4, cli.getBairroCliente());
			pst.setString(5, cli.getCidadeCliente());
			pst.setString(6, cli.getCepCliente());
			pst.setDate(7, (Date) cli.getNascimentoCliente());
			
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Inserido com sucesso.";
			} else {
				return "Erro ao inserir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String alterar(Cliente cli) {
		String sql = "update Hospedagem set nomeCliente=?,rgCliente=?,bairroCliente=?,cidadeCliente=?,cepCliente=?,nascimentoCliente=? where codCliente=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cli.getCodCliente());
			pst.setString(2, cli.getNomeCliente());
			pst.setString(3, cli.getRgCliente());
			pst.setString(4, cli.getBairroCliente());
			pst.setString(5, cli.getCidadeCliente());
			pst.setString(6, cli.getCepCliente());
			pst.setDate(7, (Date) cli.getNascimentoCliente());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Alterado com sucesso.";
			} else {
				return "Erro ao alterar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String excluir(Cliente cli) {
		String sql = "delete from Cliente where codCliente=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cli.getCodCliente());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			} else {
				return "Erro ao excluir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public List<Cliente> listarTodos() {
		String sql = "select * from Cliente";
		Connection con = ConnectionFactory.getConnection();
		List<Cliente> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.setCodCliente(rs.getInt(1));
					cli.setNomeCliente(rs.getString(2));
					cli.setRgCliente(rs.getString(3));
					cli.setBairroCliente(rs.getString(4));
					cli.setCidadeCliente(rs.getString(5));
					cli.setCepCliente(rs.getString(6));
					cli.setNascimentoCliente(rs.getDate(7));
					
					lista.add(cli);
				}
				return lista;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public Cliente pesquisarPorCod(Integer cod) {
		String sql = "select * from Cliente where codCliente=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Cliente cli = new Cliente();
				cli.setCodCliente(rs.getInt(1));
				cli.setNomeCliente(rs.getString(2));
				cli.setRgCliente(rs.getString(3));
				cli.setBairroCliente(rs.getString(4));
				cli.setCidadeCliente(rs.getString(5));
				cli.setCepCliente(rs.getString(6));
				cli.setNascimentoCliente(rs.getDate(7));
				
				return cli;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}
}
