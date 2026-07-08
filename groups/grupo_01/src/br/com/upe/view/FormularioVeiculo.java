package br.com.upe.view;

import br.com.upe.service.GeminiPlannerService;
import br.com.upe.service.IAPlannerService;

import javax.swing.*;
import java.awt.*;

public class FormularioVeiculo extends JDialog {

    private JTextField txtModelo, txtAutonomia, txtBateria, txtConector;
    private JTextArea txtIAInput;
    private JButton btnProcessarIA, btnSalvar;
    private final IAPlannerService iaService;
    private boolean salvo = false;

    public FormularioVeiculo(Frame parent) {
        super(parent, "Cadastrar Veículo", true);
        setSize(450, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        this.iaService = new GeminiPlannerService();

        JPanel painelIA = new JPanel(new BorderLayout(5, 5));
        painelIA.setBorder(BorderFactory.createTitledBorder("⚡ Cadastro Automático via IA"));
        txtIAInput = new JTextArea(3, 30);
        txtIAInput.setLineWrap(true);
        txtIAInput.setWrapStyleWord(true);
        txtIAInput.setToolTipText("Ex: Cadastra um BYD Dolphin da marca BYD com 400km de autonomia, bateria de 44.9 kWh e conector CCS2");
        btnProcessarIA = new JButton("Preencher Campos");
        painelIA.add(new JScrollPane(txtIAInput), BorderLayout.CENTER);
        painelIA.add(btnProcessarIA, BorderLayout.SOUTH);
        add(painelIA, BorderLayout.NORTH);


        JPanel painelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        painelForm.add(new JLabel("Modelo:")); txtModelo = new JTextField(); painelForm.add(txtModelo);
        painelForm.add(new JLabel("Autonomia (km):")); txtAutonomia = new JTextField(); painelForm.add(txtAutonomia);
        painelForm.add(new JLabel("Capacidade Bateria (kWh):")); txtBateria = new JTextField(); painelForm.add(txtBateria);
        painelForm.add(new JLabel("Tipo de Conector:")); txtConector = new JTextField(); painelForm.add(txtConector);

        add(painelForm, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Cadastro");
        painelAcoes.add(btnSalvar);
        add(painelAcoes, BorderLayout.SOUTH);

        configurarEventos();
    }

    private void configurarEventos() {
        btnProcessarIA.addActionListener(e -> {
            String textoLivre = txtIAInput.getText().trim();
            if (textoLivre.isEmpty()) return;

            btnProcessarIA.setEnabled(false);
            btnProcessarIA.setText("Processando...");

            new Thread(() -> {
                try {
                    String instrucoes = "Veículo contendo exatamente nesta ordem: Modelo; Autonomia; Capacidade da Bateria; Tipo de Conector";
                    String resultado = iaService.processarCadastroAutomatico(textoLivre, instrucoes);
                    String[] dados = resultado.split(";");

                    SwingUtilities.invokeLater(() -> {
                        if (dados.length >= 4) {
                            txtModelo.setText(dados[0].trim());
                            txtAutonomia.setText(dados[1].trim());
                            txtBateria.setText(dados[2].trim());
                            txtConector.setText(dados[3].trim());
                        }
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Erro na IA: " + ex.getMessage()));
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        btnProcessarIA.setEnabled(true);
                        btnProcessarIA.setText("Preencher Campos");
                    });
                }
            }).start();
        });

        btnSalvar.addActionListener(e -> { salvo = true; dispose(); });
    }

    public boolean isSalvo() { return salvo; }
    public String getModelo() { return txtModelo.getText(); }
    public String getAutonomia() { return txtAutonomia.getText(); }
    public String getBateria() { return txtBateria.getText(); }
    public String getConector() { return txtConector.getText(); }
}