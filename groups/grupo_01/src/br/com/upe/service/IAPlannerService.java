package br.com.upe.service;

public interface IAPlannerService {
    String processarCadastroAutomatico(String textoLivre, String tipoEntidade);

    String planejarRotaInteligente(String veiculoInfo, String cidadeDestinoInfo);
}