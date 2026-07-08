package br.com.upe.view;

import br.com.upe.service.GeminiPlannerService;
import br.com.upe.service.IAPlannerService;

import javax.swing.*;
import java.awt.*;

public class FormularioEletroposto extends JDialog {

    private JTextField txtNome, txtLocalizacao, txtIdCidade, txtConectores, txtPotencia, txtPreco, txtVagas;
    private JTextArea txtIAInput;
    private JButton btnProcessarIA, btnSalvar;
    private final IAPlannerService iaService;
    private boolean salvo = false;

    public FormularioEletroposto(Frame parent) {
        super(parent, "Cadastrar Eletroposto", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        this.iaService = new GeminiPlannerService();


        JPanel painelIA = new JPanel(new BorderLayout(5, 5));
        painelIA.setBorder(BorderFactory.createTitledBorder("⚡ Cadastro Automático via IA"));
        txtIAInput = new JTextArea(3, 30);
        txtIAInput.setLineWrap(true);
        txtIAInput.setWrapStyleWord(true);
        btnProcessarIA = new JButton("Preencher Campos");
        painelIA.add(new JScrollPane(txtIAInput), BorderLayout.CENTER);
        painelIA.add(btnProcessarIA, BorderLayout.SOUTH);
        add(painelIA, BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridLayout(7, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        painelForm.add(new JLabel("Nome do Posto:")); txtNome = new JTextField(); painelForm.add(txtNome);
        painelForm.add(new JLabel("Localização (Endereço):")); txtLocalizacao = new JTextField(); painelForm.add(txtLocalizacao);
        painelForm.add(new JLabel("ID da Cidade:")); txtIdCidade = new JTextField(); painelForm.add(txtIdCidade);
        painelForm.add(new JLabel("Conectores (ex: CCS2, Tipo 2):")); txtConectores = new JTextField(); painelForm.add(txtConectores);
        painelForm.add(new JLabel("Potência (kW):")); txtPotencia = new JTextField(); painelForm.add(txtPotencia);
        painelForm.add(new JLabel("Preço por kWh (R$):")); txtPreco = new JTextField(); painelForm.add(txtPreco);
        painelForm.add(new JLabel("Vagas Disponíveis:")); txtVagas = new JTextField(); painelForm.add(txtVagas);

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
                    String instrucoes = "Eletroposto contendo exatamente nesta ordem: Nome; Localização; ID Cidade; Conectores; Potência; Preço; Vagas";
                    String resultado = iaService.processarCadastroAutomatico(textoLivre, instrucoes);
                    String[] dados = resultado.split(";");

                    SwingUtilities.invokeLater(() -> {
                        if (dados.length >= 7) {
                            txtNome.setText(dados[0].trim());
                            txtLocalizacao.setText(dados[1].trim());
                            txtIdCidade.setText(dados[2].trim());
                            txtConectores.setText(dados[3].trim());
                            txtPotencia.setText(dados[4].trim());
                            txtPreco.setText(dados[5].trim());
                            txtVagas.setText(dados[6].trim());
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
    public String getNome() { return txtNome.getText(); }
    public String getLocalizacao() { return txtLocalizacao.getText(); }
    public String getIdCidade() { return txtIdCidade.getText(); }
    public String getConectores() { return txtConectores.getText(); }
    public String getPotencia() { return txtPotencia.getText(); }
    public String getPreco() { return txtPreco.getText(); }
    public String getVagas() { return txtVagas.getText(); }
}