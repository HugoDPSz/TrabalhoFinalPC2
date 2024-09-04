package controller;

import Imp.ClienteDAOImp;
import connection.ConnectionFactory;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    public String inserir(Cliente cliente){
        String sql = "insert into cliente(codCliente, nomeCliente, rgCliente, bairroCliente, cidadeCliente, cepCliente, nascimentoCliente) values (?,?,?,?,?,?,?)";
        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cliente.getCodCliente());
            pst.setString(2, cliente.getNomeCliente());
            pst.setString(3, cliente.getRgCliente());
            pst.setString(4, cliente.getBairroCliente());
            pst.setString(5, cliente.getCidadeCliente());
            pst.setString(6, cliente.getCepCliente());
            pst.setDate(7, new java.sql.Date(cliente.getNascimentoCliente().getTime()));

            int res = pst.executeUpdate();
            if(res > 0){
                return "Inserido com sucesso.";
            }else{
                return "Erro ao inserir.";
            }
        }catch(SQLException e){
            return e.getMessage();
        } finally {
            ConnectionFactory.close(con);
        }
    }
    public List<Cliente> read() {
        String sql = "select * from cliente";
        Connection con = ConnectionFactory.getConnection();
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("codCliente"),
                            rs.getString("nomeCliente"),
                            rs.getString("rgCliente"),
                            rs.getString("bairroCliente"),
                            rs.getString("cidadeCliente"),
                            rs.getString("cepCliente"),
                            rs.getDate("nascimentoCliente")
                    );
                    lista.add(cliente);
                }
                return lista;
            } else {
                return null; // Caso não existam registros
            }
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionFactory.close(con);
        }
    }


}
