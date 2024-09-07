package Form;

import model.Chale;
import persistencia.ChaleDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChaleForm extends JFrame {

    private final ChaleDAOImp chaleDAO = new ChaleDAOImp();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código", "Localização", "Capacidade", "Valor Alta Estação", "Valor Baixa Estação"}, 0);

    private JTextField codChaleField;
    private JTextField localizacaoField;
    private JTextField capacidadeField;
    private JTextField valorAltaEstacaoField;
    private JTextField valorBaixaEstacaoField;
    private JTable table;

    public ChaleForm() {
        setTitle("Gerenciamento de Chalés");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Tabela
        tableModel.setRowCount(0); // Inicializa a tabela vazia
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2));

        formPanel.add(new JLabel("Código:"));
        codChaleField = new JTextField();
        formPanel.add(codChaleField);

        formPanel.add(new JLabel("Localização:"));
        localizacaoField = new JTextField();
        formPanel.add(localizacaoField);

        formPanel.add(new JLabel("Capacidade:"));
        capacidadeField = new JTextField();
        formPanel.add(capacidadeField);

        formPanel.add(new JLabel("Valor Alta Estação:"));
        valorAltaEstacaoField = new JTextField();
        formPanel.add(valorAltaEstacaoField);

        formPanel.add(new JLabel("Valor Baixa Estação:"));
        valorBaixaEstacaoField = new JTextField();
        formPanel.add(valorBaixaEstacaoField);

        add(formPanel, BorderLayout.NORTH);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar Todos");
        JButton createItemButton = new JButton("Criar Item do Chalé");
        JButton manageItemButton = new JButton("Gerenciar Itens");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addChale();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChale();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteChale();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllChales();
            }
        });

        createItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openItemsForm();
            }
        });

        manageItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChaleItemsForm();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);
        buttonPanel.add(createItemButton);
        buttonPanel.add(manageItemButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addChale() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            String localizacao = localizacaoField.getText();
            int capacidade = Integer.parseInt(capacidadeField.getText());
            double valorAltaEstacao = Double.parseDouble(valorAltaEstacaoField.getText());
            double valorBaixaEstacao = Double.parseDouble(valorBaixaEstacaoField.getText());

            Chale chale = new Chale();
            chale.setCodChale(codChale);
            chale.setLocalizacao(localizacao);
            chale.setCapacidade(capacidade);
            chale.setValorAltaEstacao(valorAltaEstacao);
            chale.setValorBaixaEstacao(valorBaixaEstacao);

            String result = chaleDAO.inserir(chale);
            showMessage(result);
        } catch (NumberFormatException e) {
            showMessage("Erro: Preencha todos os campos corretamente.");
        }
    }

    private void updateChale() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            String localizacao = localizacaoField.getText();
            int capacidade = Integer.parseInt(capacidadeField.getText());
            double valorAltaEstacao = Double.parseDouble(valorAltaEstacaoField.getText());
            double valorBaixaEstacao = Double.parseDouble(valorBaixaEstacaoField.getText());

            Chale chale = new Chale();
            chale.setCodChale(codChale);
            chale.setLocalizacao(localizacao);
            chale.setCapacidade(capacidade);
            chale.setValorAltaEstacao(valorAltaEstacao);
            chale.setValorBaixaEstacao(valorBaixaEstacao);

            String result = chaleDAO.alterar(chale);
            showMessage(result);
        } catch (NumberFormatException e) {
            showMessage("Erro: Preencha todos os campos corretamente.");
        }
    }

    private void deleteChale() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            String result = chaleDAO.excluir(codChale);
            showMessage(result);
        } catch (NumberFormatException e) {
            showMessage("Erro: Código inválido.");
        }
    }

    private void listAllChales() {
        List<Chale> chales = chaleDAO.listarTodos();
        tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
        if (chales != null) {
            for (Chale chale : chales) {
                tableModel.addRow(new Object[]{
                        chale.getCodChale(),
                        chale.getLocalizacao(),
                        chale.getCapacidade(),
                        chale.getValorAltaEstacao(),
                        chale.getValorBaixaEstacao()
                });
            }
        }
    }

    private void openItemsForm() {
        try {
            int codChale = Integer.parseInt(codChaleField.getText());
            ItemsForm itemsForm = new ItemsForm(codChale); // Passa o código do chalé
            itemsForm.setVisible(true);
        } catch (NumberFormatException e) {
            showMessage("Erro: Código do chalé inválido.");
        }
    }

    private void openChaleItemsForm() {
        // Abre o formulário para gerenciar os itens dos chalés
        ChaleItemsForm chaleItemsForm = new ChaleItemsForm();
        chaleItemsForm.setVisible(true);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChaleForm().setVisible(true);
        });
    }
}
