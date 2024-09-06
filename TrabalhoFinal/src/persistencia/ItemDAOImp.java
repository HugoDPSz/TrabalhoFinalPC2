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
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, item.getNomeItem());
            pst.setString(2, item.getDescricaoItem());

            int res = pst.executeUpdate();
            if (res > 0) {
                return "Item inserido com sucesso.";
            } else {
                return "Erro ao inserir o item.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            ConnectionFactory.close(con);
        }
    }

    @Override
    public String alterar(Item item) {
        String sql = "UPDATE Item SET descricaoItem = ? WHERE nomeItem = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, item.getDescricaoItem());
            pst.setString(2, item.getNomeItem());

            int res = pst.executeUpdate();
            if (res > 0) {
                return "Item alterado com sucesso.";
            } else {
                return "Erro ao alterar o item.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            ConnectionFactory.close(con);
        }
    }

    @Override
    public String excluir(String nomeItem) {
        String sql = "DELETE FROM Item WHERE nomeItem = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nomeItem);

            int res = pst.executeUpdate();
            if (res > 0) {
                return "Item excluído com sucesso.";
            } else {
                return "Erro ao excluir o item.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Item buscarPorNome(String nomeItem) {
        String sql = "SELECT * FROM Item WHERE nomeItem = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nomeItem);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Item item = new Item();
                item.setNomeItem(rs.getString("nomeItem"));
                item.setDescricaoItem(rs.getString("descricao"));
                return item;
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
    public List<Item> listarTodos() {
        String sql = "SELECT * FROM Item";
        Connection con = ConnectionFactory.getConnection();
        List<Item> lista = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setNomeItem(rs.getString("nomeItem"));
                item.setDescricaoItem(rs.getString("descricaoItem")); // Corrigido para "descricaoItem"
                lista.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
            // Lista permanece vazia
        } finally {
            ConnectionFactory.close(con);
        }
        return lista; // Retorna a lista (vazia se ocorreu um erro)
    }

}
