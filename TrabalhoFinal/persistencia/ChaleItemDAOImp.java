package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChaleItem;

public class ChaleItemDAOImp implements ChaleItemDAO {

    @Override
    public String inserir(ChaleItem ci) {
        String sql = "INSERT INTO Chale_Item (codChale, nomeItem) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, ci.getCodChale());
            pst.setString(2, ci.getNomeItem());
            int res = pst.executeUpdate();
            return res > 0 ? "Inserido com sucesso." : "Erro ao inserir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String alterar(ChaleItem ci) {
        String sql = "UPDATE Chale_Item SET nomeItem = ? WHERE codChale = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, ci.getNomeItem());
            pst.setInt(2, ci.getCodChale());
            int res = pst.executeUpdate();
            return res > 0 ? "Alterado com sucesso." : "Erro ao alterar.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String excluir(int codChale, String nomeItem) {
        String sql = "DELETE FROM Chale_Item WHERE codChale = ? AND nomeItem = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codChale);
            pst.setString(2, nomeItem);
            int res = pst.executeUpdate();
            return res > 0 ? "Excluído com sucesso." : "Erro ao excluir.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<ChaleItem> listarTodos() {
        String sql = "SELECT * FROM Chale_Item";
        List<ChaleItem> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                ChaleItem ci = new ChaleItem();
                ci.setCodChale(rs.getInt("codChale"));
                ci.setNomeItem(rs.getString("nomeItem"));
                lista.add(ci);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ChaleItems: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ChaleItem pesquisarPorCodItem(int codChale, String nomeItem) {
        String sql = "SELECT * FROM Chale_Item WHERE codChale = ? AND nomeItem = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codChale);
            pst.setString(2, nomeItem);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    ChaleItem ci = new ChaleItem();
                    ci.setCodChale(rs.getInt("codChale"));
                    ci.setNomeItem(rs.getString("nomeItem"));
                    return ci;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar ChaleItem: " + e.getMessage());
        }
        return null;
    }
}
