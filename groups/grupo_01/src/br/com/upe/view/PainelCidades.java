package br.com.upe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelCidades extends JPanel {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnExcluir;

    public PainelCidades() {

        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Estado (UF)", "Distância da Capital (km)"};

        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnCadastrar = new JButton("Cadastrar Cidade");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        modeloTabela.addRow(new Object[]{1, "Recife", "PE", 0.0});


        btnCadastrar.addActionListener(e -> {
            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioCidade form = new FormularioCidade(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    int novoId = modeloTabela.getRowCount() + 1;

                    String distanciaTexto = form.getDistancia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();

                    double distancia = distanciaTexto.isEmpty() ? 0.0 : Double.parseDouble(distanciaTexto);

                    modeloTabela.addRow(new Object[]{
                            novoId,
                            form.getNome(),
                            form.getEstado(),
                            distancia
                    });

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao converter a distância da cidade.");
                    ex.printStackTrace();
                }
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma cidade na tabela para editar.");
                return;
            }

            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioCidade form = new FormularioCidade(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    String distanciaTexto = form.getDistancia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    double distancia = distanciaTexto.isEmpty() ? 0.0 : Double.parseDouble(distanciaTexto);

                    // Atualiza a linha selecionada em vez de adicionar uma nova
                    modeloTabela.setValueAt(form.getNome(), linhaSelecionada, 1);
                    modeloTabela.setValueAt(form.getEstado(), linhaSelecionada, 2);
                    modeloTabela.setValueAt(distancia, linhaSelecionada, 3);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao converter os dados na edição.");
                    ex.printStackTrace();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma cidade na tabela para excluir.");
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta cidade?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(linhaSelecionada);
            }
        });


    }
    public javax.swing.table.DefaultTableModel getModeloTabela() {
        return this.modeloTabela;
    }
}