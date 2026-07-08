package br.com.upe.view;

import br.com.upe.service.GeminiPlannerService;
import br.com.upe.service.IAPlannerService;

import javax.swing.*;
import java.awt.*;

public class PainelPlanejador extends JPanel {

    private JComboBox<String> cbVeiculos;
    private JComboBox<String> cbDestinos;
    private JButton btnGerarRota;
    private JTextArea txtRelatorio;

    private final IAPlannerService iaService;

    public PainelPlanejador() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        this.iaService = new GeminiPlannerService();


        JPanel painelSelecao = new JPanel(new GridLayout(2, 2, 10, 10));
        painelSelecao.setBorder(BorderFactory.createTitledBorder("Configurações da Viagem"));

        painelSelecao.add(new JLabel("Selecione o Veículo Elétrico:"));

        cbVeiculos = new JComboBox<>();
        painelSelecao.add(cbVeiculos);

        painelSelecao.add(new JLabel("Selecione a Cidade de Destino:"));
        cbDestinos = new JComboBox<>();
        painelSelecao.add(cbDestinos);

        add(painelSelecao, BorderLayout.NORTH);


        JPanel painelResultado = new JPanel(new BorderLayout(5, 5));
        painelResultado.setBorder(BorderFactory.createTitledBorder("Relatório de Viagem Inteligente (Gerado pela LLM)"));

        txtRelatorio = new JTextArea();
        txtRelatorio.setEditable(false);
        txtRelatorio.setLineWrap(true);
        txtRelatorio.setWrapStyleWord(true);
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtRelatorio.setText("Configure o veículo e o destino acima e clique em 'Gerar Rota Inteligente' para calcular o relatório...");

        painelResultado.add(new JScrollPane(txtRelatorio), BorderLayout.CENTER);
        add(painelResultado, BorderLayout.CENTER);


        btnGerarRota = new JButton("🚀 Gerar Rota Inteligente via Gemini");
        btnGerarRota.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGerarRota.setBackground(new Color(34, 139, 34));
        btnGerarRota.setForeground(Color.WHITE);
        add(btnGerarRota, BorderLayout.SOUTH);


        configurarEventos();
    }

    private void configurarEventos() {
        btnGerarRota.addActionListener(e -> {
            String veiculoSelecionado = (String) cbVeiculos.getSelectedItem();
            String destinoSelecionado = (String) cbDestinos.getSelectedItem();

            btnGerarRota.setEnabled(false);
            btnGerarRota.setText("Gemini computando parâmetros de viagem...");
            txtRelatorio.setText("Aguarde... O Gemini está analisando a autonomia do carro, pontos de recarga fictícios no caminho, condições climáticas e trânsito simulado para gerar o seu cronograma...");


            new Thread(() -> {
                try {
                    String relatorioGerado = iaService.planejarRotaInteligente(veiculoSelecionado, destinoSelecionado);

                    SwingUtilities.invokeLater(() -> txtRelatorio.setText(relatorioGerado));

                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        txtRelatorio.setText("Erro ao gerar a rota: " + ex.getMessage());
                        JOptionPane.showMessageDialog(this, "Falha na comunicação com o Gemini.");
                    });
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        btnGerarRota.setEnabled(true);
                        btnGerarRota.setText("🚀 Gerar Rota Inteligente via Gemini");
                    });
                }
            }).start();
        });
    }
    public void atualizarCombos(javax.swing.table.DefaultTableModel modelCidades, javax.swing.table.DefaultTableModel modelVeiculos) {
        cbDestinos.removeAllItems();
        for (int i = 0; i < modelCidades.getRowCount(); i++) {
            String nome = modelCidades.getValueAt(i, 1).toString();
            String estado = modelCidades.getValueAt(i, 2).toString();
            String distancia = modelCidades.getValueAt(i, 3).toString();
            cbDestinos.addItem(nome + " - " + estado + " (" + distancia + " km da Capital)");
        }

        cbVeiculos.removeAllItems();
        for (int i = 0; i < modelVeiculos.getRowCount(); i++) {
            String modelo = modelVeiculos.getValueAt(i, 1).toString();
            String autonomia = modelVeiculos.getValueAt(i, 2).toString(); // Índice 2 na tabela é Autonomia
            String carga = modelVeiculos.getValueAt(i, 3).toString();     // Índice 3 na tabela é Carga Atual
            String consumo = modelVeiculos.getValueAt(i, 4).toString();   // Índice 4 na tabela é Consumo

            cbVeiculos.addItem(modelo + " (Autonomia: " + autonomia + "km | Carga: " + carga + "% | Consumo: " + consumo + " kWh/km)");
        }
    }

}