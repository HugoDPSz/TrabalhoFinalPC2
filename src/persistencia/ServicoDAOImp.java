package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Servico;

public class ServicoDAOImp implements ServicoDAO {

    @Override
    public String inserir(Servico servico) {
        String sql = "INSERT INTO Servico (codServico, nomeServico, valorServico) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, servico.getCodServico());
            pst.setString(2, servico.getNomeServico());
            pst.setInt(3, servico.getValorServico());
            int res = pst.executeUpdate();
            return res > 0 ? "Inserido com sucesso." : "Erro ao inserir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String alterar(Servico servico) {
        String sql = "UPDATE Servico SET nomeServico = ?, valorServico = ? WHERE codServico = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, servico.getNomeServico());
            pst.setInt(2, servico.getValorServico());
            pst.setInt(3, servico.getCodServico());
            int res = pst.executeUpdate();
            return res > 0 ? "Alterado com sucesso." : "Erro ao alterar.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String excluir(Integer codServico) {
        String sql = "DELETE FROM Servico WHERE codServico = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codServico);
            int res = pst.executeUpdate();
            return res > 0 ? "Excluído com sucesso." : "Erro ao excluir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM Servico";
        List<Servico> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setCodServico(rs.getInt("codServico"));
                servico.setNomeServico(rs.getString("nomeServico"));
                servico.setValorServico(rs.getInt("valorServico"));
                lista.add(servico);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    @Override
    public Servico pesquisarPorCod(Integer codServico) {
        String sql = "SELECT * FROM Servico WHERE codServico = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codServico);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Servico servico = new Servico();
                    servico.setCodServico(rs.getInt("codServico"));
                    servico.setNomeServico(rs.getString("nomeServico"));
                    servico.setValorServico(rs.getInt("valorServico"));
                    return servico;
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
