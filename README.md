# 📊 Análise de Exclusão Digital no Brasil

Este projeto é um dashboard de visualização de dados desenvolvido como parte de um trabalho acadêmico. A aplicação, construída em JavaFX, consome dados da Tabela 7313 do IBGE (PNAD Contínua) e os apresenta em três abas analíticas, permitindo a exploração de métricas sobre o acesso à internet em domicílios brasileiros.

![Uma captura de tela da aplicação mostrando a aba "Análise Gráfica" com um gráfico de barras e um gráfico de pizza](assets/Print.png)

---

## 📝 Contexto do Projeto e Objetivos

> **Nota do Autor:** Este é um primeiro projeto acadêmico. O foco principal foi aprender a arquitetura de um sistema (DAO, Service, Controller), conectar a uma base de dados e exibir os resultados em uma interface gráfica.
>
> Por ser um projeto de base para uma apresentação, ele provavelmente carece de polimento fino, tratamento de todos os casos de exceção ou funcionalidades mais avançadas. No entanto, ele serve como uma prova de conceito funcional que cumpre os objetivos acadêmicos propostos.

O objetivo central deste trabalho é analisar o panorama da exclusão digital no Brasil, com foco na evolução do acesso à internet nos domicílios.

Os objetivos específicos da análise são:
* Identificar o **crescimento** do acesso à internet nos domicílios.
* Analisar as **diferenças** entre os tipos de conexão (banda larga fixa, móvel, ambas).
* Comparar as **desigualdades regionais** entre Norte, Nordeste, Sudeste, Sul e Centro-Oeste.

---

## ✨ Funcionalidades (Features)

A aplicação é dividida em três abas principais:

### 1. 📁 Dados Brutos
* Permite a consulta direta dos dados do banco.
* **Seleção Dinâmica de Colunas:** O usuário pode usar *CheckBoxes* para selecionar quais métricas (colunas) deseja exibir na tabela, incluindo um botão "Selecionar Todos".
* Filtros por Tabela (Fonte de Dados), Ano e Região.

### 2. 📈 Análise Gráfica
* Contém filtros independentes para a geração de gráficos.
* **Gráfico de Barras (Crescimento):** Mostra o crescimento percentual (ano a ano) de uma única métrica (ex: "Banda Larga Fixa") para uma região específica.
* **Gráfico de Pizza (Distribuição):** Mostra a distribuição (market share) de uma métrica entre as 5 regiões do Brasil em um ano específico.
* **Títulos Dinâmicos:** Os gráficos possuem títulos que se atualizam automaticamente para descrever a análise sendo exibida (ex: "Crescimento de 'B. Larga Fixa' (Região Nordeste) | Fonte: Domicílios por Conexão").

### 3. 📉 Crescimento Múltiplo
* Uma tabela que exibe a variação percentual (ano a ano) de **todas as 10 métricas** simultaneamente para uma região selecionada.
* Permite uma análise panorâmica de quais tecnologias estão crescendo ou diminuindo em relação às outras.
* Todos os valores percentuais são formatados para exibir **duas casas decimais**.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21)
* **Interface Gráfica (GUI):** JavaFX
* **Gerenciador de Pacotes:** Apache Maven
* **Banco de Dados:** PostgreSQL
* **Conexão DB:** JDBC (PostgreSQL Driver)
* **Arquitetura:** Padrão de 3 camadas (Controller, Service, DAO)
    * **DAO (Data Access Object):** Um `GenericDao` foi implementado para centralizar toda a lógica de busca (JDBC), enquanto DAOs concretos (ex: `DomicilioInternetPorConexaoDao`) apenas especificam o nome da tabela.

---

## 🚀 Como Executar

### Pré-requisitos
1.  **Java 21 (ou superior)**: O projeto foi compilado com o JDK 21.
2.  **PostgreSQL**: Um servidor PostgreSQL deve estar rodando.
3.  **Banco de Dados**: Crie um banco chamado `inclusao_digital`.
4.  **Tabelas**: Execute os scripts SQL localizados na pasta `/database` para criar as tabelas e inserir os dados.

### 1. Construir (Empacotar) o Projeto

O projeto usa Maven. Para criar o `.jar` executável, rode o comando do Maven "package" (pela sua IDE ou pelo terminal):

```bash
mvn package