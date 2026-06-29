# Configuração do Gemini AI no Projeto GreenRoute

---

## Estrutura do projeto


```
projeto-GreenRoute/
|
|-- .env                            <-- Arquivo com a GEMINI_API_KEY (nao versionar no Git)
|-- pom.xml                         <-- Dependencias do Maven (langchain4j, gemini, dotenv)
|
|-- src/
|   |-- Main.java
|   | -- ...
|   |-- gemini/
|   |   |-- ConexaoGemini.java      <-- Classe de conexao com o Gemini AI
|
|-- target/                         <-- Gerado automaticamente pelo Maven (nao versionar)
```

> **Atenção:** A estrutura exibida acima é apenas um exemplo evidenciando que `.env` e `pom.xml` devem estar na pasta raiz do projeto. Todo o resto é maleável, ou seja, depende de como você estruturou o projeto!
> 
> **Destaque:** Os arquivos essenciais para a integracao com o Gemini sao o `.env` (na raiz), o `pom.xml` (na raiz) e a classe `ConexaoGemini.java` (dentro de `src/gemini/`).

---

## Passo 1 -- Criar a API Key no Google AI Studio

1. Acesse o site do Google AI Studio: **https://aistudio.google.com**
2. Faca login com sua conta Google (Gmail).
3. No menu lateral esquerdo, clique em **"Get API Key"** (ou "Obter chave de API").
4. Clique no botao **"Create API Key"** (ou "Criar chave de API").
5. Escolha o projeto desejado ou crie um novo quando solicitado.
6. A chave sera gerada automaticamente. **Copie a chave e guarde em um local seguro** -- ela sera usada no Passo 4.

> **Atencao:** Nunca compartilhe ou commite sua API Key no repositorio.

---

## Passo 2 -- Configurar o `pom.xml`

Crie o arquivo `pom.xml` na raiz do projeto e adicione as seguintes dependências:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.greenroute</groupId>
    <artifactId>projeto-GreenRoute</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Core do LangChain4j (Contem os recursos principais, memoria, etc.) -->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j</artifactId>
            <version>0.36.2</version>
        </dependency>

        <!-- Integracao especifica com o Google AI Gemini -->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-google-ai-gemini</artifactId>
            <version>0.36.2</version>
        </dependency>

        <dependency>
            <groupId>io.github.cdimascio</groupId>
            <artifactId>dotenv-java</artifactId>
            <version>3.0.2</version>
        </dependency>
    </dependencies>
</project>
```

### Entendendo as dependencias

| Dependencia | Funcao |
|---|---|
| `langchain4j` | Framework principal para integracao com modelos de IA |
| `langchain4j-google-ai-gemini` | Modulo especifico para conexao com o Google Gemini |
| `dotenv-java` | Le variaveis de ambiente a partir de um arquivo `.env` |

Apos adicionar as dependencias, recarregue o projeto no IntelliJ IDEA clicando com o botao direito no `pom.xml` e selecionando **"Maven > Reload Project"**, ou clique no icone de sincronizacao do Maven.

---

## Passo 3 -- Criar o arquivo `.env` na raiz do projeto

Crie um arquivo chamado `.env` na **raiz do projeto** (mesma pasta do `pom.xml`) com o seguinte conteudo:

```env
GEMINI_API_KEY=cole_sua_api_key_aqui
```

Substitua `cole_sua_api_key_aqui` pela chave gerada no **Passo 1**.

Exemplo:

```env
GEMINI_API_KEY=Exemplo-De-Chave-123456789
```

> **Importante:** O arquivo `.env` nao deve ser versionado no Git. Adicione a seguinte linha no `.gitignore` do projeto, caso exista:
>
> ```
> .env
> ```

---

## Passo 4 -- Criar a classe `ConexaoGemini`

Crie o arquivo `ConexaoGemini.java` dentro do pacote `gemini` (`src/gemini/ConexaoGemini.java`):

```java
package gemini;

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
```

### Entendendo o codigo

| Trecho | Funcao |
|---|---|
| `Dotenv.load()` | Carrega as variaveis do arquivo `.env` |
| `dotenv.get("GEMINI_API_KEY")` | Recupera a chave da API do arquivo `.env` |
| `GoogleAiGeminiChatModel.builder()` | Cria a conexao com o modelo Gemini |
| `modelName("gemini-2.5-flash")` | Define qual modelo do Gemini sera usado |
| `ChatRequest.builder()` | Monta a requisicao com a mensagem do usuario |
| `model.chat(request)` | Envia a requisicao ao Gemini e recebe a resposta |
| `response.aiMessage().text()` | Extrai o texto da resposta |

---

## Passo 5 -- Executar e testar

1. Certifique-se de que o arquivo `.env` esta na raiz do projeto com a chave correta.
2. Execute a classe `ConexaoGemini` pelo IntelliJ IDEA (botao direito na classe > **Run 'ConexaoGemini.main()'**).
3. Se tudo estiver configurado corretamente, voce vera no console:

```
Enviando pergunta ao Gemini...

--- Resposta do Gemini ---
(resposta gerada pelo Gemini)
```

---

## Solucao de problemas

| Problema | Solucao |
|---|---|
| `Dotenv file not found` | Verifique se o arquivo `.env` esta na raiz do projeto (mesma pasta do `pom.xml`) |
| `Unauthorized` ou erro 401 | Verifique se a API Key esta correta no arquivo `.env` |
| `ClassNotFoundException` | Recarregue as dependencias do Maven no IntelliJ |
| `API key not valid` | Va ao Google AI Studio e verifique se a chave esta ativa |

---

## Diferencas por IDE: VSCode e Eclipse

Os passos 1 a 4 sao iguais independente da IDE. As unicas diferencas estao em **recarregar as dependencias do Maven** (Passo 2) e **executar a classe** (Passo 5).

### VSCode

**Recarregar dependencias do Maven (Passo 2):**
1. Instale a extensao **"Extension Pack for Java"** (Microsoft) no VSCode, caso ainda nao tenha.
2. Apos editar o `pom.xml`, abra a paleta de comandos com `Ctrl + Shift + P`.
3. Digite e selecione **"Java: Clean Java Language Server Workspace"**.
4. Confirme a reinicializacao quando solicitado. O VSCode ira recarregar as dependencias automaticamente.

> Alternativamente, na aba **"MAVEN"** no explorer lateral, clique no icone de refresh no projeto.

**Executar a classe (Passo 5):**
1. Abra o arquivo `ConexaoGemini.java`.
2. Clique no botao **"Run"** que aparece acima do metodo `main`, ou pressione `Ctrl + F5`.
3. O resultado sera exibido no **terminal integrado** do VSCode.

---

### Eclipse

**Recarregar dependencias do Maven (Passo 2):**
1. Certifique-se de que possui o **"Eclipse IDE for Enterprise Java and Web Developers"** (inclui suporte a Maven nativo).
2. Apos editar o `pom.xml`, clique com o botao direito no projeto no **Package Explorer**.
3. Selecione **"Maven > Update Project..."** (ou pressione `Alt + F5`).
4. Marque a opcao **"Force Update of Snapshots/Releases"** e clique em **"OK"**.

**Executar a classe (Passo 5):**
1. No **Package Explorer**, navegue ate `src/gemini/ConexaoGemini.java`.
2. Clique com o botao direito no arquivo > **"Run As > Java Application"**.
3. O resultado sera exibido na aba **"Console"** do Eclipse.
