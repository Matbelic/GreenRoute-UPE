package br.com.upe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelVeiculos extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JButton btnCadastrar, btnEditar, btnExcluir;

    public PainelVeiculos() {
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Modelo", "Autonomia Máx (km)", "Carga Atual (%)", "Consumo (kWh/km)", "Tipo"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCadastrar = new JButton("Cadastrar Veículo");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.SOUTH);

        modeloTabela.addRow(new Object[]{1, "BYD Dolphin", 400.0, 85.0, 0.15, "Elétrico"});

        btnCadastrar.addActionListener(e -> {
            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioVeiculo form = new FormularioVeiculo(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    int novoId = modeloTabela.getRowCount() + 1;

                    String autonomiaTexto = form.getAutonomia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    String bateriaTexto = form.getBateria().replaceAll("[^0-9.,]", "").replace(",", ".").trim();

                    double autonomia = autonomiaTexto.isEmpty() ? 0.0 : Double.parseDouble(autonomiaTexto);
                    double bateria = bateriaTexto.isEmpty() ? 0.0 : Double.parseDouble(bateriaTexto);

                    modeloTabela.addRow(new Object[]{
                            novoId,
                            form.getModelo(),
                            autonomia,
                            100.0,
                            0.15,
                            form.getConector()
                    });

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao converter os dados numéricos do veículo.");
                    ex.printStackTrace();
                }
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela para editar.");
                return;
            }

            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioVeiculo form = new FormularioVeiculo(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    String autonomiaTexto = form.getAutonomia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    double autonomia = autonomiaTexto.isEmpty() ? 0.0 : Double.parseDouble(autonomiaTexto);

                    // Atualiza a linha do veículo respeitando a ordem correta das colunas
                    modeloTabela.setValueAt(form.getModelo(), linhaSelecionada, 1);
                    modeloTabela.setValueAt(autonomia, linhaSelecionada, 2);
                    modeloTabela.setValueAt(100.0, linhaSelecionada, 3); // Reseta a carga para fins de teste
                    modeloTabela.setValueAt(0.15, linhaSelecionada, 4);  // Mantém consumo padrão
                    modeloTabela.setValueAt(form.getConector(), linhaSelecionada, 5);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao converter os dados na edição do veículo.");
                    ex.printStackTrace();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela para excluir.");
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este veículo?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(linhaSelecionada);
            }
        });

    }
    public javax.swing.table.DefaultTableModel getModeloTabela() {
        return this.modeloTabela;
    }
}