package Form;

import model.ChaleItem;
import persistencia.ChaleItemDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChaleItemsForm extends JFrame {

    private final ChaleItemDAOImp chaleItemDAO = new ChaleItemDAOImp();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código do Chalé", "Nome do Item"}, 0);

    private JTextField codChaleField;
    private JTextField nomeItemField;
    private JTable table;

    public ChaleItemsForm() {
        setTitle("Gerenciamento de Itens do Chalé");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Tabela
        tableModel.setRowCount(0); // Inicializa a tabela vazia
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        formPanel.add(new JLabel("Código do Chalé:"));
        codChaleField = new JTextField();
        formPanel.add(codChaleField);

        formPanel.add(new JLabel("Nome do Item:"));
        nomeItemField = new JTextField();
        formPanel.add(nomeItemField);

        add(formPanel, BorderLayout.NORTH);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar Todos");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addChaleItem();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteChaleItem();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllChaleItems();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addChaleItem() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            String nomeItem = nomeItemField.getText();

            ChaleItem chaleItem = new ChaleItem();
            chaleItem.setCodChale(codChale);
            chaleItem.setNomeItem(nomeItem);

            String result = chaleItemDAO.inserir(chaleItem);
            showMessage(result);
        } catch (NumberFormatException e) {
            showMessage("Erro: Código do chalé inválido.");
        }
    }

    private void deleteChaleItem() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            String nomeItem = nomeItemField.getText();

            String result = chaleItemDAO.excluir(codChale, nomeItem);
            showMessage(result);
        } catch (NumberFormatException e) {
            showMessage("Erro: Código do chalé inválido.");
        }
    }

    private void listAllChaleItems() {
        List<ChaleItem> chaleItems = chaleItemDAO.listarTodos();
        tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
        if (chaleItems != null) {
            for (ChaleItem chaleItem : chaleItems) {
                tableModel.addRow(new Object[]{
                        chaleItem.getCodChale(),
                        chaleItem.getNomeItem()
                });
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChaleItemsForm().setVisible(true);
        });
    }
}
