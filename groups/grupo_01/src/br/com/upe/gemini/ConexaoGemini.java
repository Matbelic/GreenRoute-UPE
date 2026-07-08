package br.com.upe.gemini;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexaoGemini {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("GEMINI_API_KEY");

        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .build();

        System.out.println("Enviando pergunta ao Gemini...");

        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from("Me fale de forma breve qual a cor do ceu"))
                .build();

        ChatResponse response = model.chat(request);

        String respostaTexto = response.aiMessage().text();

        System.out.println("\n--- Resposta do Gemini ---");
        System.out.println(respostaTexto);
    }
}