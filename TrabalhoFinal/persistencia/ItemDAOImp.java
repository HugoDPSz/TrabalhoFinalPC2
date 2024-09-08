package persistencia;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImp implements ItemDAO {

    @Override
    public String inserir(Item item) {
        String sql = "INSERT INTO Item (nomeItem, descricaoItem) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, item.getNomeItem());
            pst.setString(2, item.getDescricaoItem());
            int res = pst.executeUpdate();
            return res > 0 ? "Item inserido com sucesso." : "Erro ao inserir o item.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String excluir(String nomeItem) {
        String sql = "DELETE FROM Item WHERE nomeItem = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nomeItem);
            int res = pst.executeUpdate();
            return res > 0 ? "Item excluído com sucesso." : "Erro ao excluir o item.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public Item buscarPorNome(String nomeItem) {
        String sql = "SELECT * FROM Item WHERE nomeItem = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nomeItem);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setNomeItem(rs.getString("nomeItem"));
                    item.setDescricaoItem(rs.getString("descricaoItem"));
                    return item;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar item por nome: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Item> listarTodos() {
        String sql = "SELECT * FROM Item";
        List<Item> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setNomeItem(rs.getString("nomeItem"));
                item.setDescricaoItem(rs.getString("descricaoItem"));
                lista.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
        }
        return lista;
    }
}
