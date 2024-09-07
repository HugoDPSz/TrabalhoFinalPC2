package Form;

import model.Item;
import persistencia.ItemDAOImp;
import persistencia.ChaleItemDAOImp;
import model.ChaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemsForm extends JFrame {

    private final int codChale;
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Nome do Item", "Descrição"}, 0);
    private JTable table;
    private final ItemDAOImp itemDAO = new ItemDAOImp();
    private final ChaleItemDAOImp chaleItemDAO = new ChaleItemDAOImp();

    // Campos de entrada para nome e descrição do item
    private JTextField nomeItemField;
    private JTextArea descricaoItemArea;

    public ItemsForm(int codChale) {
        this.codChale = codChale;
        setTitle("Gerenciar Itens do Chalé");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        formPanel.add(new JLabel("Nome do Item:"));
        nomeItemField = new JTextField();
        formPanel.add(nomeItemField);

        formPanel.add(new JLabel("Descrição:"));
        descricaoItemArea = new JTextArea(3, 20);
        JScrollPane descricaoScrollPane = new JScrollPane(descricaoItemArea);
        formPanel.add(descricaoScrollPane);

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar Itens ao Chalé");
        JButton listItemsButton = new JButton("Listar Itens Disponíveis");
        JButton deleteButton = new JButton("Excluir Item");

        listItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAvailableItems();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemsToChale();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        buttonPanel.add(listItemsButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void listAvailableItems() {
        try {
            List<Item> items = itemDAO.listarTodos();
            tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
            if (items != null) {
                for (Item item : items) {
                    tableModel.addRow(new Object[]{item.getNomeItem(), item.getDescricaoItem()});
                }
            }
        } catch (Exception e) {
            showMessage("Erro ao listar itens: " + e.getMessage());
        }
    }

    private void addItemsToChale() {
        String nomeItem = nomeItemField.getText().trim();
        String descricaoItem = descricaoItemArea.getText().trim();

        if (nomeItem.isEmpty() || descricaoItem.isEmpty()) {
            showMessage("Erro: Nome e descrição do item não podem estar vazios.");
            return;
        }

        try {
            Item item = new Item();
            item.setNomeItem(nomeItem);
            item.setDescricaoItem(descricaoItem);

            String result = itemDAO.inserir(item);
            if ("Sucesso".equals(result)) {
                ChaleItem chaleItem = new ChaleItem();
                chaleItem.setCodChale(codChale);
                chaleItem.setNomeItem(nomeItem);
                chaleItemDAO.inserir(chaleItem);
                showMessage("Itens adicionados ao chalé com sucesso.");
            }
        } catch (Exception e) {
            showMessage("Erro ao adicionar item");
        }
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String itemName = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o item " + itemName + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String result = itemDAO.excluir(itemName);
                    if ("Sucesso".equals(result)) {
                        listAvailableItems(); // Atualiza a lista de itens
                        showMessage("Deletado.");
                    } else {
                        showMessage("Erro ao deletar");
                    }
                } catch (Exception e) {
                    showMessage("Erro ao excluir item" );
                }
            }
        } else {
            showMessage("Selecione um item para excluir.");
        }
    }

    private List<Item> getSelectedItems() {
        List<Item> selectedItems = new ArrayList<>();
        int[] selectedRows = table.getSelectedRows();
        for (int row : selectedRows) {
            String itemName = (String) tableModel.getValueAt(row, 0);
            try {
                Item item = itemDAO.buscarPorNome(itemName);
                if (item != null) {
                    selectedItems.add(item);
                }
            } catch (Exception e) {
                showMessage("Erro ao buscar item: " + e.getMessage());
            }
        }
        return selectedItems;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
