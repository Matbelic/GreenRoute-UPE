package br.com.upe.view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("GreenRoute - Sistema Inteligente de Logística");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JTabbedPane abas = new JTabbedPane();


        PainelCidades painelCidades = new PainelCidades();
        PainelEletropostos painelEletropostos = new PainelEletropostos();
        PainelVeiculos painelVeiculos = new PainelVeiculos();
        PainelPlanejador painelPlanejador = new PainelPlanejador();


        abas.addTab("Cidades", painelCidades);
        abas.addTab("Eletropostos", painelEletropostos);
        abas.addTab("Veículos", painelVeiculos);
        abas.addTab("Planejador IA", painelPlanejador);

        abas.addChangeListener(e -> {
            if (abas.getSelectedIndex() == 3) {
                painelPlanejador.atualizarCombos(
                        painelCidades.getModeloTabela(),
                        painelVeiculos.getModeloTabela()
                );
            }
        });


        add(abas, BorderLayout.CENTER);
    }

    private JPanel criarPainelProvisorio(String texto) {
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.add(new JLabel(texto));
        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}