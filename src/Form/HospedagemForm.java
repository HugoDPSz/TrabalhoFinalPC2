package Form;

import model.Hospedagem;
import persistencia.HospedagemDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class HospedagemForm extends JFrame {

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código", "Código do Chalé", "Código do Cliente", "Estado", "Data Início", "Data Fim", "Qtd Pessoas", "Desconto", "Valor Final"}, 0);
    private JTable table;
    private final HospedagemDAOImp hospedagemDAO = new HospedagemDAOImp();

    // Campos de entrada para hospedagem
    private JTextField codHospedagemField;
    private JTextField codChaleField;
    private JTextField codClienteField;
    private JTextField estadoField;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JTextField qtdPessoasField;
    private JTextField descontoField;
    private JTextField valorFinalField;

    public HospedagemForm() {
        setTitle("Gerenciar Hospedagens");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(9, 2));

        formPanel.add(new JLabel("Código:"));
        codHospedagemField = new JTextField();
        formPanel.add(codHospedagemField);

        formPanel.add(new JLabel("Código do Chalé:"));
        codChaleField = new JTextField();
        formPanel.add(codChaleField);

        formPanel.add(new JLabel("Código do Cliente:"));
        codClienteField = new JTextField();
        formPanel.add(codClienteField);

        formPanel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        formPanel.add(estadoField);

        formPanel.add(new JLabel("Data Início (YYYY-MM-DD):"));
        dataInicioField = new JTextField();
        formPanel.add(dataInicioField);

        formPanel.add(new JLabel("Data Fim (YYYY-MM-DD):"));
        dataFimField = new JTextField();
        formPanel.add(dataFimField);

        formPanel.add(new JLabel("Qtd Pessoas:"));
        qtdPessoasField = new JTextField();
        formPanel.add(qtdPessoasField);

        formPanel.add(new JLabel("Desconto:"));
        descontoField = new JTextField();
        formPanel.add(descontoField);

        formPanel.add(new JLabel("Valor Final:"));
        valorFinalField = new JTextField();
        formPanel.add(valorFinalField);

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
        JButton hospServicoButton = new JButton("Solicitar serviço");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHospedagem();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHospedagem();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHospedagem();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listHospedagens();
            }
        });

        hospServicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HospedagemServicoForm().setVisible(true);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);
        buttonPanel.add(hospServicoButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addHospedagem() {
        try {
            Hospedagem hos = new Hospedagem();
            hos.setCodHospedagem(Integer.parseInt(codHospedagemField.getText().trim()));
            hos.setCodChale(Integer.parseInt(codChaleField.getText().trim()));
            hos.setCodCliente(Integer.parseInt(codClienteField.getText().trim()));
            hos.setEstado(estadoField.getText().trim());
            hos.setDataInicio(Date.valueOf(dataInicioField.getText().trim()));
            hos.setDataFim(Date.valueOf(dataFimField.getText().trim()));
            hos.setQtdPessoas(Integer.parseInt(qtdPessoasField.getText().trim()));
            hos.setDesconto(Double.parseDouble(descontoField.getText().trim()));
            hos.setValorFinal(Double.parseDouble(valorFinalField.getText().trim()));

            String result = hospedagemDAO.inserir(hos);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao adicionar hospedagem: " + e.getMessage());
        }
    }

    private void updateHospedagem() {
        try {
            Hospedagem hos = new Hospedagem();
            hos.setCodHospedagem(Integer.parseInt(codHospedagemField.getText().trim()));
            hos.setCodChale(Integer.parseInt(codChaleField.getText().trim()));
            hos.setCodCliente(Integer.parseInt(codClienteField.getText().trim()));
            hos.setEstado(estadoField.getText().trim());
            hos.setDataInicio(Date.valueOf(dataInicioField.getText().trim()));
            hos.setDataFim(Date.valueOf(dataFimField.getText().trim()));
            hos.setQtdPessoas(Integer.parseInt(qtdPessoasField.getText().trim()));
            hos.setDesconto(Double.parseDouble(descontoField.getText().trim()));
            hos.setValorFinal(Double.parseDouble(valorFinalField.getText().trim()));

            String result = hospedagemDAO.alterar(hos);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao atualizar hospedagem: " + e.getMessage());
        }
    }

    private void deleteHospedagem() {
        try {
            int codHospedagem = Integer.parseInt(codHospedagemField.getText().trim());
            Hospedagem hos = new Hospedagem();
            hos.setCodHospedagem(codHospedagem);
            String result = hospedagemDAO.excluir(codHospedagem);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao excluir hospedagem: " + e.getMessage());
        }
    }

    private void listHospedagens() {
        try {
            List<Hospedagem> hospedagens = hospedagemDAO.listarTodos();
            tableModel.setRowCount(0);
            if (hospedagens != null) {
                for (Hospedagem hos : hospedagens) {
                    tableModel.addRow(new Object[]{
                            hos.getCodHospedagem(),
                            hos.getCodChale(),
                            hos.getCodCliente(),
                            hos.getEstado(),
                            hos.getDataInicio(),
                            hos.getDataFim(),
                            hos.getQtdPessoas(),
                            hos.getDesconto(),
                            hos.getValorFinal()
                    });
                }
            }
        } catch (Exception e) {
            showMessage("Erro ao listar hospedagens: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
