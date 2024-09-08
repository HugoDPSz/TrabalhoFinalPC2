package Form;

import model.Item;

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

        setLayout(new GridLayout(5, 1, 10, 10));

        JButton chaleButton = new JButton("Chalé");
        JButton hospedagemButton = new JButton("Hospedagem");
        JButton clienteButton = new JButton("Cliente");
        JButton itemButton = new JButton("Criar item");
        JButton servicoButton = new JButton("Criar serviço");

        chaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChaleForm().setVisible(true);
            }
        });

        hospedagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new HospedagemForm().setVisible(true);
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteForm().setVisible(true);
            }
        });

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ItemsForm().setVisible(true);
            }
        });

        servicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ServicoForm().setVisible(true);
            }
        });

        add(chaleButton);
        add(hospedagemButton);
        add(clienteButton);
        add(itemButton);
        add(servicoButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}
