package view;

import java.sql.Connection;
import java.sql.Date;
import model.*;
import persistencia.*;

public class Teste {
	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnection();
		if (con != null) {
			System.out.println("Conexão estabelecida com sucesso.");
			ConnectionFactory.close(con);
		} else {
			System.out.println("Falha ao estabelecer conexão.");
			return;
		}

	}
}
