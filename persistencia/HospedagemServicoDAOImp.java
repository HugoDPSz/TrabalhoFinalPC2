package persistencia;

import model.HospedagemServico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospedagemServicoDAOImp implements HospedagemServicoDAO {

    @Override
    public String inserir(HospedagemServico hospedagemServico) {
        String sql = "INSERT INTO hospedagem_servico (codHospedagem, dataServico, codServico) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, hospedagemServico.getCodHospedagem());
            pst.setDate(2, Date.valueOf(hospedagemServico.getDataServico()));
            pst.setInt(3, hospedagemServico.getCodServico());
            int res = pst.executeUpdate();
            return res > 0 ? "Inserido com sucesso." : "Erro ao inserir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String alterar(HospedagemServico hospedagemServico) {
        String sql = "UPDATE hospedagem_servico SET dataServico = ?, codServico = ? WHERE codHospedagem = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, Date.valueOf(hospedagemServico.getDataServico()));
            pst.setInt(2, hospedagemServico.getCodServico());
            pst.setInt(3, hospedagemServico.getCodHospedagem());
            int res = pst.executeUpdate();
            return res > 0 ? "Alterado com sucesso." : "Erro ao alterar.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String excluir(HospedagemServico hospedagemServico) {
        String sql = "DELETE FROM hospedagem_servico WHERE codHospedagem = ? AND dataServico = ? AND codServico = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, hospedagemServico.getCodHospedagem());
            pst.setDate(2, Date.valueOf(hospedagemServico.getDataServico()));
            pst.setInt(3, hospedagemServico.getCodServico());
            int res = pst.executeUpdate();
            return res > 0 ? "Excluído com sucesso." : "Erro ao excluir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<HospedagemServico> listarTodos() {
        String sql = "SELECT * FROM hospedagem_servico";
        List<HospedagemServico> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                HospedagemServico hospedagemServico = new HospedagemServico();
                hospedagemServico.setCodHospedagem(rs.getInt("codHospedagem"));
                hospedagemServico.setDataServico(rs.getDate("dataServico").toLocalDate());
                hospedagemServico.setCodServico(rs.getInt("codServico"));
                lista.add(hospedagemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public HospedagemServico pesquisarPorCod(Integer codHospedagem, Integer codServico) {
        String sql = "SELECT * FROM hospedagem_servico WHERE codHospedagem = ? AND codServico = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codHospedagem);
            pst.setInt(2, codServico);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    HospedagemServico hospedagemServico = new HospedagemServico();
                    hospedagemServico.setCodHospedagem(rs.getInt("codHospedagem"));
                    hospedagemServico.setDataServico(rs.getDate("dataServico").toLocalDate()); // Correção aqui
                    hospedagemServico.setCodServico(rs.getInt("codServico"));
                    return hospedagemServico;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        return null;
    }
}
