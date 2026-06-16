# GreenRoute — Intelligent Logistics System for Electric and Hybrid Vehicles / Sistema Inteligente de Logística para Veículos Elétricos e Híbridos

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Architecture-MVC-blue?style=for-the-badge" alt="MVC">
  <img src="https://img.shields.io/badge/UPE-Surubim-red?style=for-the-badge" alt="UPE">
</p>

---

### 🌐 Language / Idioma
*   [Read in English (#-english)](#-english)
*   [Ler em Português (#-português)](#-português)

---

## 🇺🇸 English

The **GreenRoute** is an intercity route management system designed to mitigate the logistical planning challenges imposed by vehicle electrification[cite: 2]. The system manages fleets of vehicles (electric and hybrid), urban city networks, and the available charging station infrastructure using a modular architecture and Artificial Intelligence integration[cite: 2].

This central repository serves as the **Official Course Hub**, centralizing the implementations and solutions developed by the student teams[cite: 2].

### 📌 Context and Rationale
The transition to sustainable mobility requires rigorous autonomy planning[cite: 2]. Dynamic factors such as distance between municipalities, maximum road speed, charging connector compatibility, and energy consumption rates determine the feasibility of a trip[cite: 2].

The project simulates a logistical ecosystem structured strictly under the **MVC (Model-View-Controller)** architectural pattern, divided into two major software maturity stages[cite: 2]:
1.  **Module 1:** Static in-memory persistence, robust manipulation of native data structures (Arrays), and a console-based interface[cite: 2].
2.  **Module 2:** Graphical User Interface (GUI), dynamic collections (ArrayList), and intelligent decision-making integrated with Large Language Models (LLM) via API[cite: 2].

### 🏗️ Technical Specification of Entities (Model)
The core of the system consists of the strict mapping of the following entities and attributes[cite: 2]:

#### 🧩 Entity Diagram
*   **`Veiculo`** *(Abstract Class)*[cite: 2]
    *   `int id` (Sequential unique numerical identifier)[cite: 2]
    *   `String modelo`[cite: 2]
    *   `double autonomiaMaxima` (in km with a full charge)[cite: 2]
    *   `double cargaBateriaAtual` (percentage from 0.0 to 100.0)[cite: 2]
    *   `double consumoKwhPorKm` (average energy consumption rate)[cite: 2]
    *   `int tempoRecargaCompleta` (estimated time in minutes for 0% to 100% recharge)[cite: 2]
    *   **`VeiculoEletrico`** *(Subclass)*[cite: 2]
        *   `String tipoConector` (e.g., "Tipo 2", "CCS2", "CHAdeMO")[cite: 2]
        *   `int tempoRecargaRapida` (in minutes using high-power chargers)[cite: 2]
    *   **`VeiculoHibrido`** *(Subclass)*[cite: 2]
        *   `double capacidadeTanqueCombustivel` (in liters)[cite: 2]
        *   `double consumoCombustivel` (in km/l for the combustion engine)[cite: 2]
        *   `String tipoCombustivel` (e.g., "Gasolina", "Etanol")[cite: 2]
*   **`Eletroposto`**[cite: 2]
    *   `int id` (Unique numerical identifier)[cite: 2]
    *   `String nome`[cite: 2]
    *   `String localizacao` (Address/Highway)[cite: 2]
    *   `int cidadeId` (Numerical link to the City where it is located)[cite: 2]
    *   `String tiposConectoresDisponiveis` (e.g., "CCS2, Tipo 2" - accepts multiple)[cite: 2]
    *   `double potenciaCargaKw` (Charger power rating in kW)[cite: 2]
    *   `double precoPorKwh` (Price charged per kilowatt-hour)[cite: 2]
    *   `int vagasDisponiveis`[cite: 2]
*   **`Cidade`**[cite: 2]
    *   `int id` (IBGE code or unique numerical identifier)[cite: 2]
    *   `String nome`[cite: 2]
    *   `String estado` (State/UF)[cite: 2]
    *   `double distanciaDaCapital` (in km)[cite: 2]

### 🎯 Modules and Requirements Timeline
#### 🔹 Module 1: Base Structure and CRUD with Arrays (50% of the Grade)
*   **Architecture:** Strict division into `model`, `repository`, and `controller` packages[cite: 2].
*   **Data Structures:** Mandatory and exclusive use of **Traditional Arrays (`[]`)** for data storage in repositories, remaining robust as allocation increases over time[cite: 2].
*   **User Interface:** Interactive Command Line (Console) structured with `while` loops and `switch/case` conditionals[cite: 2].
*   **Initial Business Rule:** Route simulation calculating whether the vehicle's current autonomy (`autonomiaMaxima * (cargaBateriaAtual / 100)`) is sufficient to cover the destination distance[cite: 2]. If insufficient, the controller performs a linear search to suggest available charging stations based on the `cidadeId`[cite: 2].

#### 🔹 Module 2: GUI, ArrayList, and LLM-Powered Automated Routing (50% of the Grade)
*   **Refactoring:** Complete migration from fixed arrays to **`java.util.ArrayList`** dynamic collections[cite: 2].
*   **Graphical User Interface (GUI):** The console interface is completely abandoned[cite: 2]. All management screens must be developed in **Java Swing** or **JavaFX** using visual tables and modals[cite: 2].
*   **Exception Handling:** Custom exceptions (e.g., `AutonomiaInsuficienteException`, `ConectorIncompativelException`)[cite: 2]. Errors must generate user-friendly visual alerts (`JOptionPane`) and never crash the system[cite: 2].
*   **AI Integration (LLM):** 
    *   *Action 1 (Quick Setup):* Processing free text through an LLM to extract structured data and automatically populate GUI forms[cite: 2].
    *   *Action 2 (Smart Route Planner):* A decision-making engine that sends vehicle specifications and remaining autonomy combined with real-time simulated data (traffic, weather) to generate descriptive route recommendations using the LLM[cite: 2].
*   **Bonus Feature:** Integration with the **Google Maps API** to visually plot routes or calculate real distances via the Distance Matrix API[cite: 2].

### 🚀 How to Contribute / Submit the Project (Student Instructions)
To avoid merge conflicts on the `main` branch, all submissions will be isolated in subdirectories[cite: 2]. Please follow this protocol:
1.  **Fork** this central repository to your GitHub account[cite: 2].
2.  In your Fork, navigate to the `/grupos/` directory and create a subfolder named after your group number (e.g., `/grupos/grupo_03/`)[cite: 2].
3.  Develop your team's entire project inside that specific folder[cite: 2].
4.  Ensure that your commits are made within the deadline set for each module[cite: 2].
5.  Open a **Pull Request (PR)** targeting the `main` branch of this repository using the title format: `[Módulo X] - Grupo XX - StudentName1 and StudentName2`[cite: 2].

### 🧑‍🏫 Guidance and Coordination
*   **Advising Professor:** Marcondes Ricarte da Silva Júnior
*   **Institution:** University of Pernambuco (UPE) — Surubim Campus
*   **Context:** Practical Evaluation - Object-Oriented Programming / Software Engineering

<p align="center">
  Developed for academic and scientific purposes within the AI & Mobility solution ecosystem. ⭐ Leave a star on the repository to support the project!
</p>

---

## 🇧🇷 Português

O **GreenRoute** é um sistema de gerenciamento de rotas intermunicipais projetado para mitigar os desafios de planejamento logístico impostos pela eletrificação veicular[cite: 2]. O sistema gerencia frotas de veículos (elétricos e híbridos), malhas rodoviárias de cidades e a infraestrutura de eletropostos disponíveis, utilizando arquitetura modular e integração com Inteligência Artificial[cite: 2].

Este repositório central funciona como o **Hub Oficial da Disciplina**, centralizando as implementações e soluções desenvolvidas pelas equipes de estudantes[cite: 2].

### 📌 Contexto e Justificativa
A transição para a mobilidade sustentável exige um planejamento rigoroso de autonomia[cite: 2]. Fatores dinâmicos como a distância entre municípios, a velocidade máxima das vias, a compatibilidade de conectores de eletropostos e a taxa de consumo energético determinam a viabilidade de uma viagem[cite: 2]. 

O projeto simula um ecossistema logístico estruturado estritamente sob o padrão arquitetural **MVC (Model-View-Controller)**, dividindo-se em duas grandes etapas de maturidade de software[cite: 2]:
1.  **Módulo 1:** Persistência estática em memória, manipulação robusta de estruturas de dados nativas (Arrays) e interface via console[cite: 2].
2.  **Módulo 2:** Interface Gráfica (GUI), coleções dinâmicas (ArrayList) e tomada de decisão inteligente integrada a Large Language Models (LLM) via API[cite: 2].

### 🏗️ Especificação Técnica das Entidades (Model)
O núcleo do sistema é composto pelo mapeamento rigoroso das seguintes entidades e atributos[cite: 2]:

#### 🧩 Diagrama de Entidades
*   **`Veiculo`** *(Classe Abstrata)*[cite: 2]
    *   `int id` (Identificador numérico único sequencial)[cite: 2]
    *   `String modelo`[cite: 2]
    *   `double autonomiaMaxima` (em km com carga completa)[cite: 2]
    *   `double cargaBateriaAtual` (porcentagem de 0.0 a 100.0)[cite: 2]
    *   `double consumoKwhPorKm` (taxa média de consumo)[cite: 2]
    *   `int tempoRecargaCompleta` (tempo em minutos de 0% a 100%)[cite: 2]
    *   **`VeiculoEletrico`** *(Subclasse)*[cite: 2]
        *   `String tipoConector` (Ex: "Tipo 2", "CCS2", "CHAdeMO")[cite: 2]
        *   `int tempoRecargaRapida` (em minutos em alta potência)[cite: 2]
    *   **`VeiculoHibrido`** *(Subclasse)*[cite: 2]
        *   `double capacidadeTanqueCombustivel` (em litros)[cite: 2]
        *   `double consumoCombustivel` (em km/l no motor a combustão)[cite: 2]
        *   `String tipoCombustivel` (Ex: "Gasolina", "Etanol")[cite: 2]
*   **`Eletroposto`**[cite: 2]
    *   `int id` (Identificador numérico único)[cite: 2]
    *   `String nome`[cite: 2]
    *   `String localizacao` (Endereço/Rodovia)[cite: 2]
    *   `int cidadeId` (Vínculo numérico com a Cidade)[cite: 2]
    *   `String tiposConectoresDisponiveis` (Ex: "CCS2, Tipo 2" - múltiplos)[cite: 2]
    *   `double potenciaCargaKw` (Ex: 50.0 Kw, 150.0 Kw)[cite: 2]
    *   `double precoPorKwh` (Valor cobrado pelo kWh)[cite: 2]
    *   `int vagasDisponiveis`[cite: 2]
*   **`Cidade`**[cite: 2]
    *   `int id` (Código IBGE ou identificador único)[cite: 2]
    *   `String nome`[cite: 2]
    *   `String estado` (UF)[cite: 2]
    *   `double distanciaDaCapital` (em km)[cite: 2]

### 🎯 Cronograma de Módulos e Requisitos
#### 🔹 Módulo 1: Estrutura Base e CRUD com Arrays (50% da Nota)
*   **Arquitetura:** Divisão estrita em pacotes `model`, `repository` e `controller`[cite: 2].
*   **Estruturas de Dados:** Uso obrigatório e exclusivo de **Arrays Tradicionais (`[]`)** para armazenamento em memória, tratando o crescimento da alocação de forma robusta[cite: 2].
*   **Interface:** Console interativo estruturado com laços `while` e condicionais `switch/case`[cite: 2].
*   **Regra de Negócio Inicial:** Simulação de rota calculando se a autonomia atual do veículo (`autonomiaMaxima * (cargaBateriaAtual / 100)`) cobre a distância até o destino[cite: 2]. Caso seja insuficiente, o controlador realiza uma busca linear nos repositórios para sugerir eletropostos compatíveis com base no `cidadeId`[cite: 2].

#### 🔹 Módulo 2: GUI, ArrayList e Roteirização Automatizada por LLM (50% da Nota)
*   **Refatoração:** Migração completa de arrays fixos para coleções dinâmicas **`java.util.ArrayList`**[cite: 2].
*   **Interface Gráfica (GUI):** Abandono completo do console[cite: 2]. Telas completas de gerenciamento desenvolvidas em **Java Swing** ou **JavaFX** com tabelas visuais e modais[cite: 2].
*   **Tratamento de Exceções:** Lançamento de exceções personalizadas (Ex: `AutonomiaInsuficienteException`, `ConectorIncompativelException`)[cite: 2]. Erros devem gerar alertas visuais amigáveis (`JOptionPane`) e nunca travar a aplicação[cite: 2].
*   **Integração com IA (LLM):** 
    *   *Ação 1 (Cadastro Rápido):* Processamento de texto livre através de IA para extração de dados estruturados e preenchimento automatizado de formulários[cite: 2].
    *   *Ação 2 (Planejador Inteligente):* Motor de tomada de decisão enviando dados de autonomia e recarga combinados com dados simulados em tempo real (trânsito, clima) para geração de rotas descritivas pela LLM[cite: 2].
*   **Bônus:** Integração com a **API do Google Maps** para plotagem visual de rotas ou cálculo real de matriz de distância[cite: 2].

### 🚀 Como Contribuir / Enviar o Projeto (Instruções para Alunos)
Para evitar conflitos de código na *branch main*, as entregas serão isoladas por subpastas[cite: 2]. Siga o protocolo abaixo:
1.  **Faça um Fork** deste repositório central para a sua conta do GitHub[cite: 2].
2.  No seu Fork, navegue até o diretório `/grupos/` e crie uma subpasta com o número do seu grupo (Ex: `/grupos/grupo_03/`)[cite: 2].
3.  Desenvolva todo o projeto da sua equipe dentro dessa pasta específica[cite: 2].
4.  Certifique-se de que seus commits foram realizados dentro do prazo estipulado para cada módulo[cite: 2].
5.  Abra um **Pull Request (PR)** direcionado à *branch main* deste repositório usando o padrão de título: `[Módulo X] - Grupo XX - NomeEstudante1 e NomeEstudante2`[cite: 2].

### 🧑‍🏫 Orientação e Coordenação
*   **Professor Orientador:** Marcondes Ricarte da Silva Júnior
*   **Instituição:** Universidade de Pernambuco (UPE) — Campus Surubim
*   **Contexto:** Atividade Prática de Fixação - Programação Orientada a Objetos / Engenharia de Software

<p align="center">
  Desenvolvido para fins acadêmicos e científicos no ecossistema de soluções de IA & Mobilidade. ⭐ Deixe uma estrela no repositório para apoiar o projeto!
</p>uma busca linear nos repositórios para sugerir eletropostos compatíveis com base no `cidadeId`.

### 🔹 Módulo 2: GUI, ArrayList e Roteirização Automatizada por LLM (50% da Nota)
*   **Refatoração:** Migração completa de arrays fixos para coleções dinâmicas **`java.util.ArrayList`**.
*   **Interface Gráfica (GUI):** Abandono completo do console. Telas completas de gerenciamento desenvolvidas em **Java Swing** ou **JavaFX** com tabelas visuais e modais.
*   **Tratamento de Exceções:** Lançamento de exceções personalizadas (Ex: `AutonomiaInsuficienteException`, `ConectorIncompativelException`) capturadas graficamente via alertas amigáveis (`JOptionPane`).
*   **Integração com IA (LLM):** 
    *   *Ação 1 (Cadastro Rápido):* Processamento de texto livre através de IA para extração de dados estruturados e preenchimento automatizado de formulários.
    *   *Ação 2 (Planejador Inteligente):* Motor de tomada de decisão enviando dados de autonomia e recarga combinados com dados simulados em tempo real (trânsito, clima) para geração de rotas descritivas pela LLM.
*   **Bônus:** Integração com a **API do Google Maps** para plotagem visual de rotas ou cálculo real de matriz de distância.

---

## 🚀 Como Contribuir / Enviar o Projeto (Instruções para Alunos)

Para evitar conflitos de código na *branch main*, as entregas serão isoladas por subpastas. Siga o protocolo abaixo:

1.  **Faça um Fork** deste repositório central para a sua conta do GitHub.
2.  No seu Fork, navegue até o diretório `/grupos/` e crie uma subpasta com o número do seu grupo (Ex: `/grupos/grupo_03/`).
3.  Desenvolva todo o projeto da sua equipe dentro dessa pasta específica.
4.  Certifique-se de que seus commits foram realizados dentro do prazo estipulado para cada módulo.
5.  Abra um **Pull Request (PR)** direcionado à *branch main* deste repositório usando o padrão de título: `[Módulo X] - Grupo XX - NomeEstudante1 e NomeEstudante2`.

---

## 🧑‍🏫 Orientação e Coordenação

*   **Professor Orientador:** Marcondes Ricarte da Silva Júnior
*   **Instituição:** Universidade de Pernambuco (UPE) — Campus Surubim
*   **Contexto:** Atividade Prática de Fixação - Programação Orientada a Objetos / Engenharia de Software

---

<p align="center">
  Desenvolvido para fins acadêmicos e científicos no ecossistema de soluções de IA & Mobilidade. ⭐ Deixe uma estrela no repositório para apoiar o projeto!
</p>
