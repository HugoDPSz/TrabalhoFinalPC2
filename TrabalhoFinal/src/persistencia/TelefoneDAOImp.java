package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Telefone;

public class TelefoneDAOImp implements TelefoneDAO {

    @Override
    public void inserir(Telefone telefone) {
        String sql = "insert into Telefone(telefone, codCliente, tipoTelefone) values (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, telefone.getTelefone());
            pst.setInt(2, telefone.getCodCliente());
            pst.setString(3, telefone.getTipoTelefone());

            pst.executeUpdate();
            System.out.println("Telefone inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.close(con);
        }
    }

    @Override
    public void alterar(Telefone telefone) {
        String sql = "update Telefone set tipoTelefone=?, codCliente=? where telefone=?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, telefone.getTipoTelefone());
            pst.setInt(2, telefone.getCodCliente());
            pst.setString(3, telefone.getTelefone());

            pst.executeUpdate();
            System.out.println("Telefone alterado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.close(con);
        }
    }

    @Override
    public void excluir(String telefone) {
        String sql = "DELETE FROM Telefone WHERE telefone = ?";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, telefone);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.close(con);
        }
    }


    @Override
    public List<Telefone> listarTodos() {
        String sql = "select * from Telefone";
        Connection con = ConnectionFactory.getConnection();
        List<Telefone> lista = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Telefone tel = new Telefone();
                tel.setTelefone(rs.getString(1));
                tel.setCodCliente(rs.getInt(2));
                tel.setTipoTelefone(rs.getString(3));
                lista.add(tel);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar telefones: " + e.getMessage());
        } finally {
            ConnectionFactory.close(con);
        }
        return lista;
    }

    @Override
    public Telefone buscarPorNumero(String telefone) {
        String sql = "select * from Telefone where telefone=?";
        Connection con = ConnectionFactory.getConnection();
        Telefone tel = null;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, telefone);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tel = new Telefone();
                tel.setTelefone(rs.getString(1));
                tel.setCodCliente(rs.getInt(2));
                tel.setTipoTelefone(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.close(con);
        }
        return tel;
    }
}
