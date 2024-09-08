package Form;

import model.Servico;
import persistencia.ServicoDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ServicoForm extends JFrame {

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código", "Nome", "Valor"}, 0);
    private JTable table;
    private final ServicoDAOImp servicoDAO = new ServicoDAOImp();

    // Campos de entrada para serviço
    private JTextField codServicoField;
    private JTextField nomeServicoField;
    private JTextField valorServicoField;

    public ServicoForm() {
        setTitle("Gerenciar Serviços");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        formPanel.add(new JLabel("Código:"));
        codServicoField = new JTextField();
        formPanel.add(codServicoField);

        formPanel.add(new JLabel("Nome:"));
        nomeServicoField = new JTextField();
        formPanel.add(nomeServicoField);

        formPanel.add(new JLabel("Valor:"));
        valorServicoField = new JTextField();
        formPanel.add(valorServicoField);

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addServico();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateServico();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteServico();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listServicos();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addServico() {
        try {
            Servico servico = new Servico();
            servico.setCodServico(Integer.parseInt(codServicoField.getText().trim()));
            servico.setNomeServico(nomeServicoField.getText().trim());
            servico.setValorServico(Integer.parseInt(valorServicoField.getText().trim()));

            String result = servicoDAO.inserir(servico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao adicionar serviço: " + e.getMessage());
        }
    }

    private void updateServico() {
        try {
            Servico servico = new Servico();
            servico.setCodServico(Integer.parseInt(codServicoField.getText().trim()));
            servico.setNomeServico(nomeServicoField.getText().trim());
            servico.setValorServico(Integer.parseInt(valorServicoField.getText().trim()));

            String result = servicoDAO.alterar(servico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    private void deleteServico() {
        try {
            int codServico = Integer.parseInt(codServicoField.getText().trim());
            Servico servico = new Servico();
            servico.setCodServico(codServico);
            String result = servicoDAO.excluir(servico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao excluir serviço: " + e.getMessage());
        }
    }

    private void listServicos() {
        try {
            List<Servico> servicos = servicoDAO.listarTodos();
            tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
            if (servicos != null) {
                for (Servico servico : servicos) {
                    tableModel.addRow(new Object[]{
                            servico.getCodServico(),
                            servico.getNomeServico(),
                            servico.getValorServico()
                    });
                }
            }
        } catch (Exception e) {
            showMessage("Erro ao listar serviços: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
