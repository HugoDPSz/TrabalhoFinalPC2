package view;

import java.sql.Connection;
import Form.MainForm;
import persistencia.ConnectionFactory;

public class Main {
	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnection();
		if (con != null) {
			System.out.println("Conexão estabelecida com sucesso.");
			ConnectionFactory.close(con);
			MainForm mainForm = new MainForm();
			mainForm.setVisible(true);
		} else {
			System.out.println("Falha ao estabelecer conexão.");
		}
	}
}
