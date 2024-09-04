package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Hospedagem;

public class HospedagemDAOImp implements HospedagemDAO {

	@Override
	public String inserir(Hospedagem hos) {
		String sql = "insert into Hospedagem(codHospedagem,codChale,estado,dataInicio,dataFim,qtdPessoas,desconto,valorFinal)values (?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, hos.getCodHospedagem());
			pst.setInt(2, hos.getCodChale());
			pst.setString(3, hos.getEstado());
			pst.setDate(4, hos.getDataInicio());
			pst.setDate(5, hos.getDataFim());
			pst.setInt(6, hos.getQtdPessoas());
			pst.setDouble(7, hos.getDesconto());
			pst.setDouble(8, hos.getValorFinal());
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
		String sql = "update Hospedagem set codChale=?,estado=?,dataInicio=?,dataFim=?,qtdPessoas=?,desconto=?,valorFinal=? where codHospedagem=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, hos.getCodHospedagem());
			pst.setInt(2, hos.getCodChale());
			pst.setString(3, hos.getEstado());
			pst.setDate(4, hos.getDataInicio());
			pst.setDate(5, hos.getDataFim());
			pst.setInt(6, hos.getQtdPessoas());
			pst.setDouble(7, hos.getDesconto());
			pst.setDouble(8, hos.getValorFinal());
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
		String sql = "delete from empregado where cpf=?";
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
		String sql = "select * from empregado";
		Connection con = ConnectionFactory.getConnection();
		List<Hospedagem> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Hospedagem hos = new Hospedagem();
					hos.setCodHospedagem(rs.getInt(1));
					hos.setCodChale(rs.getInt(2));
					hos.setEstado(rs.getString(3));
					hos.setDataInicio(rs.getDate(4));
					hos.setDataFim(rs.getDate(5));
					hos.setQtdPessoas(rs.getInt(6));
					hos.setDesconto(rs.getDouble(7));
					hos.setValorFinal(rs.getDouble(8));
					lista.add(hos);
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
	public Hospedagem pesquisarPorCod(Integer cod) {
		String sql = "select * from Hospedagem where codHospedagem=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Hospedagem hos = new Hospedagem();
				hos.setCodHospedagem(rs.getInt(1));
				hos.setCodChale(rs.getInt(2));
				hos.setEstado(rs.getString(3));
				hos.setDataInicio(rs.getDate(4));
				hos.setDataFim(rs.getDate(5));
				hos.setQtdPessoas(rs.getInt(6));
				hos.setDesconto(rs.getDouble(7));
				hos.setValorFinal(rs.getDouble(8));
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
