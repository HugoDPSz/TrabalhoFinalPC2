package Form;

import model.Cliente;
import persistencia.ClienteDAOImp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteForm extends JFrame {

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código", "Nome", "RG", "Bairro", "Cidade", "Estado", "Endereço", "CEP", "Nascimento"}, 0);
    private JTable table;
    private final ClienteDAOImp clienteDAO = new ClienteDAOImp();
    private JTextField codField, nomeField, rgField, bairroField, cidadeField, estadoField, enderecoField, cepField;
    private JFormattedTextField nascimentoField;

    public ClienteForm() {
        setTitle("Gerenciar Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Campos
        JPanel formPanel = new JPanel(new GridLayout(10, 2));
        formPanel.add(new JLabel("Código:"));
        codField = new JTextField();
        formPanel.add(codField);

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("RG:"));
        rgField = new JTextField();
        formPanel.add(rgField);

        formPanel.add(new JLabel("Bairro:"));
        bairroField = new JTextField();
        formPanel.add(bairroField);

        formPanel.add(new JLabel("Cidade:"));
        cidadeField = new JTextField();
        formPanel.add(cidadeField);

        formPanel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        formPanel.add(estadoField);

        formPanel.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        formPanel.add(enderecoField);

        formPanel.add(new JLabel("CEP:"));
        cepField = new JTextField();
        formPanel.add(cepField);

        formPanel.add(new JLabel("Nascimento (YYYY-MM-DD)):"));
        nascimentoField = new JFormattedTextField();
        nascimentoField.setColumns(10);
        formPanel.add(nascimentoField);

        add(formPanel, BorderLayout.NORTH);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar");
        JButton managePhonesButton = new JButton("Gerenciar Telefones");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCliente();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCliente();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCliente();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listClientes();
            }
        });

        managePhonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTelefoneForm();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);
        buttonPanel.add(managePhonesButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addCliente() {
        Cliente cliente = new Cliente();
        cliente.setCodCliente(Integer.parseInt(codField.getText()));
        cliente.setNomeCliente(nomeField.getText());
        cliente.setRgCliente(rgField.getText());
        cliente.setBairroCliente(bairroField.getText());
        cliente.setCidadeCliente(cidadeField.getText());
        cliente.setEstadoCliente(estadoField.getText());
        cliente.setEnderecoCliente(enderecoField.getText());
        cliente.setCepCliente(cepField.getText());
        cliente.setNascimentoCliente(java.sql.Date.valueOf(nascimentoField.getText()));

        String result = clienteDAO.inserir(cliente);
        showMessage(result);
    }

    private void updateCliente() {
        Cliente cliente = new Cliente();
        cliente.setCodCliente(Integer.parseInt(codField.getText()));
        cliente.setNomeCliente(nomeField.getText());
        cliente.setRgCliente(rgField.getText());
        cliente.setBairroCliente(bairroField.getText());
        cliente.setCidadeCliente(cidadeField.getText());
        cliente.setEstadoCliente(estadoField.getText());
        cliente.setEnderecoCliente(enderecoField.getText());
        cliente.setCepCliente(cepField.getText());
        cliente.setNascimentoCliente(java.sql.Date.valueOf(nascimentoField.getText()));

        String result = clienteDAO.alterar(cliente);
        showMessage(result);
    }

    private void deleteCliente() {
        int codCliente = Integer.parseInt(codField.getText());
        Cliente cliente = new Cliente();
        cliente.setCodCliente(codCliente);

        String result = clienteDAO.excluir(cliente);
        showMessage(result);
    }

    private void listClientes() {
        List<Cliente> clientes = clienteDAO.listarTodos();
        tableModel.setRowCount(0);
        if (clientes != null) {
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{
                        cliente.getCodCliente(),
                        cliente.getNomeCliente(),
                        cliente.getRgCliente(),
                        cliente.getBairroCliente(),
                        cliente.getCidadeCliente(),
                        cliente.getEstadoCliente(),
                        cliente.getEnderecoCliente(),
                        cliente.getCepCliente(),
                        cliente.getNascimentoCliente()
                });
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void openTelefoneForm() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int codCliente = (int) tableModel.getValueAt(selectedRow, 0);
            new TelefoneForm(codCliente).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para gerenciar seus telefones.");
        }
    }

}
