package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Hospedagem;

public class HospedagemDAOImp implements HospedagemDAO {

	@Override
	public String inserir(Hospedagem hos) {
		String sql = "INSERT INTO Hospedagem (codHospedagem, codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, hos.getCodHospedagem());
			pst.setInt(2, hos.getCodChale());
			pst.setInt(3, hos.getCodCliente()); // Corrigido para incluir codCliente
			pst.setString(4, hos.getEstado());
			pst.setDate(5, hos.getDataInicio());
			pst.setDate(6, hos.getDataFim());
			pst.setInt(7, hos.getQtdPessoas());
			pst.setDouble(8, hos.getDesconto());
			pst.setDouble(9, hos.getValorFinal());
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
	public String alterar(Hospedagem hos) {
		String sql = "UPDATE Hospedagem SET codChale=?, codCliente=?, estado=?, dataInicio=?, dataFim=?, qtdPessoas=?, desconto=?, valorFinal=? WHERE codHospedagem=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, hos.getCodChale());
			pst.setInt(2, hos.getCodCliente()); // Corrigido para incluir codCliente
			pst.setString(3, hos.getEstado());
			pst.setDate(4, hos.getDataInicio());
			pst.setDate(5, hos.getDataFim());
			pst.setInt(6, hos.getQtdPessoas());
			pst.setDouble(7, hos.getDesconto());
			pst.setDouble(8, hos.getValorFinal());
			pst.setInt(9, hos.getCodHospedagem()); // Corrigido para definir codHospedagem na condição WHERE
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
	public String excluir(Hospedagem hos) {
		String sql = "DELETE FROM Hospedagem WHERE codHospedagem=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, hos.getCodHospedagem());
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
	public List<Hospedagem> listarTodos() {
		String sql = "SELECT * FROM Hospedagem";
		Connection con = ConnectionFactory.getConnection();
		List<Hospedagem> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Hospedagem hos = new Hospedagem();
				hos.setCodHospedagem(rs.getInt("codHospedagem"));
				hos.setCodChale(rs.getInt("codChale"));
				hos.setCodCliente(rs.getInt("codCliente"));
				hos.setEstado(rs.getString("estado"));
				hos.setDataInicio(rs.getDate("dataInicio"));
				hos.setDataFim(rs.getDate("dataFim"));
				hos.setQtdPessoas(rs.getInt("qtdPessoas"));
				hos.setDesconto(rs.getDouble("desconto"));
				hos.setValorFinal(rs.getDouble("valorFinal"));
				lista.add(hos);
			}
			return lista;
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public Hospedagem pesquisarPorCod(Integer codHospedagem) {
		String sql = "SELECT * FROM Hospedagem WHERE codHospedagem=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codHospedagem);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Hospedagem hos = new Hospedagem();
				hos.setCodHospedagem(rs.getInt("codHospedagem"));
				hos.setCodChale(rs.getInt("codChale"));
				hos.setCodCliente(rs.getInt("codCliente"));
				hos.setEstado(rs.getString("estado"));
				hos.setDataInicio(rs.getDate("dataInicio"));
				hos.setDataFim(rs.getDate("dataFim"));
				hos.setQtdPessoas(rs.getInt("qtdPessoas"));
				hos.setDesconto(rs.getDouble("desconto"));
				hos.setValorFinal(rs.getDouble("valorFinal"));
				return hos;
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
