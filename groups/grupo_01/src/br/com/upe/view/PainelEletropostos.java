package br.com.upe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelEletropostos extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JButton btnCadastrar, btnEditar, btnExcluir;

    public PainelEletropostos() {
        setLayout(new BorderLayout());


        String[] colunas = {"ID", "Nome", "Localização", "ID Cidade", "Conectores", "Potência (kW)", "Preço/kWh", "Vagas"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCadastrar = new JButton("Cadastrar Eletroposto");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.SOUTH);

        modeloTabela.addRow(new Object[]{1, "Posto EcoCarga", "Rodovia BR-101, Km 50", 1, "CCS2, Tipo 2", 150.0, 2.10, 4});

        btnCadastrar.addActionListener(e -> {
            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioEletroposto form = new FormularioEletroposto(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    int novoId = modeloTabela.getRowCount() + 1;

                    String idCidadeTexto = form.getIdCidade().replaceAll("[^0-9]", "").trim();
                    String potenciaTexto = form.getPotencia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    String precoTexto = form.getPreco().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    String vagasTexto = form.getVagas().replaceAll("[^0-9]", "").trim();


                    int idCidade = idCidadeTexto.isEmpty() ? 0 : Integer.parseInt(idCidadeTexto);
                    double potencia = potenciaTexto.isEmpty() ? 0.0 : Double.parseDouble(potenciaTexto);
                    double preco = precoTexto.isEmpty() ? 0.0 : Double.parseDouble(precoTexto);
                    int vagas = vagasTexto.isEmpty() ? 0 : Integer.parseInt(vagasTexto);


                    modeloTabela.addRow(new Object[]{
                            novoId,
                            form.getNome(),
                            form.getLocalizacao(),
                            idCidade,
                            form.getConectores(),
                            potencia,
                            preco,
                            vagas
                    });

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao processar os números convertidos pela IA. Certifique-se de que os campos contêm valores numéricos.",
                            "Erro de Conversão",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnEditar.addActionListener(e -> {
            int row = tabela.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um eletroposto na tabela para editar.");
                return;
            }

            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            FormularioEletroposto form = new FormularioEletroposto(framePrincipal);
            form.setVisible(true);

            if (form.isSalvo()) {
                try {
                    String potenciaTexto = form.getPotencia().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    String precoTexto = form.getPreco().replaceAll("[^0-9.,]", "").replace(",", ".").trim();
                    String vagasTexto = form.getVagas().replaceAll("[^0-9]", "").trim();

                    double potencia = potenciaTexto.isEmpty() ? 0.0 : Double.parseDouble(potenciaTexto);
                    double preco = precoTexto.isEmpty() ? 0.0 : Double.parseDouble(precoTexto);
                    int vagas = vagasTexto.isEmpty() ? 0 : Integer.parseInt(vagasTexto);

                    modeloTabela.setValueAt(form.getNome(), row, 1);
                    modeloTabela.setValueAt(form.getLocalizacao(), row, 2);
                    modeloTabela.setValueAt(form.getIdCidade(), row, 3);
                    modeloTabela.setValueAt(form.getConectores(), row, 4);
                    modeloTabela.setValueAt(potencia, row, 5);
                    modeloTabela.setValueAt(preco, row, 6);
                    modeloTabela.setValueAt(vagas, row, 7);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao converter os dados na edição do eletroposto.");
                    ex.printStackTrace();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um eletroposto na tabela para excluir.");
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este eletroposto?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(row);
            }
        });
    }
}