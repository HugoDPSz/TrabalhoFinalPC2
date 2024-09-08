package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    public MainForm() {
        setTitle("Menu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 1, 10, 10)); // Layout de 3 linhas e 1 coluna

        // Botões
        JButton chaléButton = new JButton("Chalé");
        JButton hospedagemButton = new JButton("Hospedagem");
        JButton clienteButton = new JButton("Cliente");

        // Adiciona ActionListener para abrir as respectivas telas
        chaléButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChaleForm().setVisible(true);
            }
        });

        hospedagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crie a classe HospedagemForm para a tela de Hospedagem
                // new HospedagemForm().setVisible(true);
                JOptionPane.showMessageDialog(MainForm.this, "Tela de Hospedagem ainda não criada.");
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteForm().setVisible(true);
            }
        });

        // Adiciona os botões ao painel
        add(chaléButton);
        add(hospedagemButton);
        add(clienteButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}
