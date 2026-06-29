package com.greenroute.repository;

import com.greenroute.model.Veiculo;

public class VeiculoRepository {
    private Veiculo[] veiculos;
    private int quantidade;

    public VeiculoRepository() {
        veiculos = new Veiculo[10];
        quantidade = 0;
    }

    public void adicionar(Veiculo veiculo) {
        if (quantidade < veiculos.length) {
            veiculos[quantidade] = veiculo;
            quantidade++;
        }
    }

    public Veiculo buscar(int id) {
        for (int i = 0; i < quantidade; i++) {
            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }

    public void atualizar(int id, Veiculo veiculo) {
        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {
                veiculos[i] = veiculo;
                return;
            }
        }
    }

    public void remover(int id) {
        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {

                for (int j = i; j < quantidade - 1; j++) {
                    veiculos[j] = veiculos[j + 1];
                }

                veiculos[quantidade - 1] = null;
                quantidade--;

                return;
            }
        }
    }

    public void listar() {
        System.out.println("=== LISTA DE VEÍCULOS ===");
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Veículo: " + veiculos[i].getModelo() + " - ID: " + veiculos[i].getId() + " - Autonomia: " + veiculos[i].calcularAutonomia() + " km");
        }
    }

    public int getQuantidade() {
        return quantidade;
    }
}