package br.com.upe.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class GeminiPlannerService implements IAPlannerService {

    private final ChatLanguageModel model;

    public GeminiPlannerService() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("GEMINI_API_KEY");

        this.model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .build();
    }

    @Override
    public String processarCadastroAutomatico(String textoLivre, String tipoEntidade) {
        String prompt = "Você é um assistente do sistema GreenRoute. O usuário deseja cadastrar uma " + tipoEntidade + ".\n"
                + "Extraia os dados do seguinte texto livre e retorne APENAS os valores dos atributos organizados "
                + "em uma única linha, separados por ponto e vírgula (;). Não adicione nenhuma saudação, markdown ou explicação.\n\n"
                + "Texto do usuário: " + textoLivre;


        return model.generate(prompt);
    }

    @Override
    public String planejarRotaInteligente(String veiculoInfo, String cidadeDestinoInfo) {
        String prompt = "Você é o motor de tomada de decisão do GreenRoute. Planeje uma rota intermunicipal.\n"
                + "Veículo atual: " + veiculoInfo + "\n"
                + "Destino: " + cidadeDestinoInfo + "\n"
                + "Considere fatores simulados como clima, trânsito e o tempo de recarga se necessário.\n"
                + "Gere um relatório descritivo e breve de viagem.";

        return model.generate(prompt);
    }
}