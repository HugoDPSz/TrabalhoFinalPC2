package Form;

import model.HospedagemServico;
import persistencia.HospedagemServicoDAOImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HospedagemServicoForm extends JFrame {

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Código Hospedagem", "Data Serviço", "Código Serviço"}, 0);
    private JTable table;
    private final HospedagemServicoDAOImp hospedagemServicoDAO = new HospedagemServicoDAOImp();

    private JTextField codHospedagemField;
    private JTextField dataServicoField;
    private JTextField codServicoField;

    public HospedagemServicoForm() {
        setTitle("Gerenciar Hospedagem e Serviço");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        formPanel.add(new JLabel("Código Hospedagem:"));
        codHospedagemField = new JTextField();
        formPanel.add(codHospedagemField);

        formPanel.add(new JLabel("Data Serviço (YYYY-MM-DD):"));
        dataServicoField = new JTextField();
        formPanel.add(dataServicoField);

        formPanel.add(new JLabel("Código Serviço:"));
        codServicoField = new JTextField();
        formPanel.add(codServicoField);

        add(formPanel, BorderLayout.NORTH);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHospedagemServico();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHospedagemServico();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHospedagemServico();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listHospedagemServicos();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addHospedagemServico() {
        try {
            HospedagemServico hospedagemServico = new HospedagemServico();
            hospedagemServico.setCodHospedagem(Integer.parseInt(codHospedagemField.getText().trim()));
            hospedagemServico.setDataServico(LocalDate.parse(dataServicoField.getText().trim()));
            hospedagemServico.setCodServico(Integer.parseInt(codServicoField.getText().trim()));

            String result = hospedagemServicoDAO.inserir(hospedagemServico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao adicionar hospedagem e serviço: " + e.getMessage());
        }
    }

    private void updateHospedagemServico() {
        try {
            HospedagemServico hospedagemServico = new HospedagemServico();
            hospedagemServico.setCodHospedagem(Integer.parseInt(codHospedagemField.getText().trim()));
            hospedagemServico.setDataServico(LocalDate.parse(dataServicoField.getText().trim()));
            hospedagemServico.setCodServico(Integer.parseInt(codServicoField.getText().trim()));

            String result = hospedagemServicoDAO.alterar(hospedagemServico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao atualizar hospedagem e serviço: " + e.getMessage());
        }
    }

    private void deleteHospedagemServico() {
        try {
            int codHospedagem = Integer.parseInt(codHospedagemField.getText().trim());
            LocalDate dataServico = LocalDate.parse(dataServicoField.getText().trim());
            int codServico = Integer.parseInt(codServicoField.getText().trim());

            HospedagemServico hospedagemServico = new HospedagemServico();
            hospedagemServico.setCodHospedagem(codHospedagem);
            hospedagemServico.setDataServico(dataServico);
            hospedagemServico.setCodServico(codServico);
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(dataServico);

            String result = hospedagemServicoDAO.excluir(codHospedagem, sqlDate, codServico);
            showMessage(result);
        } catch (Exception e) {
            showMessage("Erro ao excluir hospedagem e serviço: " + e.getMessage());
        }
    }

    private void listHospedagemServicos() {
        try {
            List<HospedagemServico> hospedagemServicos = hospedagemServicoDAO.listarTodos();
            tableModel.setRowCount(0);
            if (hospedagemServicos != null) {
                for (HospedagemServico hospedagemServico : hospedagemServicos) {
                    tableModel.addRow(new Object[]{
                            hospedagemServico.getCodHospedagem(),
                            hospedagemServico.getDataServico(),
                            hospedagemServico.getCodServico()
                    });
                }
            }
        } catch (Exception e) {
            showMessage("Erro ao listar hospedagens e serviços: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
