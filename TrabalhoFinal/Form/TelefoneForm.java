package Form;

import model.Telefone;
import persistencia.TelefoneDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelefoneForm extends JFrame {

    private final int codCliente;
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Telefone", "Tipo de Telefone"}, 0);
    private JTable table;
    private final TelefoneDAOImp telefoneDAO = new TelefoneDAOImp();

    public TelefoneForm(int codCliente) {
        this.codCliente = codCliente;
        setTitle("Gerenciar Telefones do Cliente " + codCliente);
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar Telefone");
        JButton updateButton = new JButton("Alterar Telefone");
        JButton deleteButton = new JButton("Excluir Telefone");
        JButton listButton = new JButton("Listar Telefones");

        // Adicionar Ação aos Botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTelefone();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTelefone();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTelefone();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listTelefones();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Lista inicial de telefones
        listTelefones();
    }

    private void addTelefone() {
        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone:");
        String tipoTelefone = JOptionPane.showInputDialog(this, "Digite o tipo do telefone:");

        if (telefone != null && tipoTelefone != null) {
            Telefone tel = new Telefone();
            tel.setTelefone(telefone);
            tel.setCodCliente(codCliente);
            tel.setTipoTelefone(tipoTelefone);
            telefoneDAO.inserir(tel);
            showMessage("Telefone adicionado com sucesso.");
            listTelefones(); // Atualiza a lista após a adição
        }
    }

    private void updateTelefone() {
        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone para alterar:");
        Telefone tel = telefoneDAO.buscarPorNumero(telefone);

        if (tel != null) {
            String novoTipo = JOptionPane.showInputDialog(this, "Digite o novo tipo do telefone:", tel.getTipoTelefone());

            if (novoTipo != null) {
                tel.setTipoTelefone(novoTipo);
                tel.setCodCliente(codCliente);
                telefoneDAO.alterar(tel);
                showMessage("Telefone alterado com sucesso.");
                listTelefones(); // Atualiza a lista após a alteração
            }
        } else {
            showMessage("Telefone não encontrado.");
        }
    }

    private void deleteTelefone() {
        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone para excluir:");

        if (telefone != null) {
            telefoneDAO.excluir(telefone);
            showMessage("Telefone excluído com sucesso.");
            listTelefones(); // Atualiza a lista após a exclusão
        }
    }

    private void listTelefones() {
        List<Telefone> telefones = telefoneDAO.listarTodos();
        tableModel.setRowCount(0);

        if (telefones != null) {
            for (Telefone tel : telefones) {
                if (tel.getCodCliente() == codCliente) {
                    tableModel.addRow(new Object[]{tel.getTelefone(), tel.getTipoTelefone()});
                }
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
