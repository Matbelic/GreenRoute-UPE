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

        // 1. Inicializa o modelo do Gemini
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey) // Substitua pela sua chave real
                .modelName("gemini-2.5-flash")
                .build();

        System.out.println("Enviando pergunta ao Gemini...");

        // 2. CORREÇÃO DA SINTAXE: usa .messages() recebendo UserMessage.from()
        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from("Me fale de forma breve qual a cor do ceu"))
                .build();

        // 3. Executa a chamada passando a requisição estruturada
        ChatResponse response = model.chat(request);

        // 4. Extrai o texto final retornado
        String respostaTexto = response.aiMessage().text();

        System.out.println("\n--- Resposta do Gemini ---");
        System.out.println(respostaTexto);
    }
}
