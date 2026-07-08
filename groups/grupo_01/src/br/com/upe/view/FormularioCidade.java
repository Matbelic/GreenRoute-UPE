package br.com.upe.view;

import br.com.upe.service.GeminiPlannerService;
import br.com.upe.service.IAPlannerService;

import javax.swing.*;
import java.awt.*;

public class FormularioCidade extends JDialog {

    private JTextField txtNome;
    private JTextField txtEstado;
    private JTextField txtDistancia;

    private JTextArea txtIAInput;
    private JButton btnProcessarIA;
    private JButton btnSalvar;

    private final IAPlannerService iaService;
    private boolean salvo = false;

    public FormularioCidade(Frame parent) {
        super(parent, "Cadastrar Cidade", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        this.iaService = new GeminiPlannerService();


        JPanel painelIA = new JPanel(new BorderLayout(5, 5));
        painelIA.setBorder(BorderFactory.createTitledBorder("⚡ Cadastro Automático via Inteligência Artificial"));

        txtIAInput = new JTextArea(3, 30);
        txtIAInput.setLineWrap(true);
        txtIAInput.setWrapStyleWord(true);
        txtIAInput.setToolTipText("Ex: Cadastre a cidade de Caruaru que fica em PE e tem 130 km de distância da capital");

        btnProcessarIA = new JButton("Preencher Campos");

        painelIA.add(new JScrollPane(txtIAInput), BorderLayout.CENTER);
        painelIA.add(btnProcessarIA, BorderLayout.SOUTH);
        add(painelIA, BorderLayout.NORTH);


        JPanel painelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        painelForm.add(new JLabel("Nome da Cidade:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Estado (UF):"));
        txtEstado = new JTextField();
        painelForm.add(txtEstado);

        painelForm.add(new JLabel("Distância da Capital (km):"));
        txtDistancia = new JTextField();
        painelForm.add(txtDistancia);

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
            if (textoLivre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite alguma descrição para a IA.");
                return;
            }

            btnProcessarIA.setEnabled(false);
            btnProcessarIA.setText("Processando com Gemini...");

            new Thread(() -> {
                try {

                    String resultado = iaService.processarCadastroAutomatico(textoLivre, "Cidade contendo Nome, Estado(UF) e Distância da Capital");

                    String[] dados = resultado.split(";");

                    SwingUtilities.invokeLater(() -> {
                        if (dados.length >= 3) {
                            txtNome.setText(dados[0].trim());
                            txtEstado.setText(dados[1].trim());
                            txtDistancia.setText(dados[2].trim());
                            JOptionPane.showMessageDialog(this, "Campos preenchidos com sucesso pela IA!");
                        } else {
                            JOptionPane.showMessageDialog(this, "A IA não conseguiu estruturar os dados perfeitamente. Tente reescrever.");
                        }
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Erro ao contatar a IA: " + ex.getMessage()));
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        btnProcessarIA.setEnabled(true);
                        btnProcessarIA.setText("Preencher Campos");
                    });
                }
            }).start();
        });

        btnSalvar.addActionListener(e -> {
            if (txtNome.getText().trim().isEmpty() || txtEstado.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e Estado são obrigatórios.");
                return;
            }
            salvo = true;
            dispose();
        });
    }

    public boolean isSalvo() { return salvo; }
    public String getNome() { return txtNome.getText().trim(); }
    public String getEstado() { return txtEstado.getText().trim(); }
    public String getDistancia() { return txtDistancia.getText().trim(); }
}