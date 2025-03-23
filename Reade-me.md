# Sudoku Game with Graphical Interface (Java)

Um jogo de Sudoku desenvolvido em Java com interface gráfica, geração aleatória de tabuleiros, validação de regras, níveis de dificuldade e funcionalidades adicionais como dicas e salvamento de progresso.

## Funcionalidades

- **Geração Aleatória de Tabuleiros**: Usa backtracking para criar tabuleiros válidos e únicos.
- **Níveis de Dificuldade**: 
  - **Fácil**: 30 células removidas.
  - **Médio**: 40 células removidas.
  - **Difícil**: 50 células removidas.
- **Validação em Tempo Real**: Verifica conflitos em linhas, colunas e subgrades 3x3.
- **Contador de Tempo**: Exibe o tempo decorrido desde o início do jogo.
- **Sistema de Dicas**: Revela números corretos (até 3 dicas por partida).
- **Salvar/Carregar Progresso**: Salva o estado atual do jogo em um arquivo (`sudoku_save.dat`).
- **Interface Gráfica Intuitiva**: Células editáveis em branco e fixas em cinza.

## Tecnologias Utilizadas
- **Java**: Linguagem principal.
- **Java Swing**: Framework para a interface gráfica.
- **Serialização**: Para salvar/carregar o estado do jogo.

## Pré-requisitos
- JDK 8 ou superior.

## Como Executar

### Passo 1: Clonar o Repositório
```bash
git clone https://github.com/JoseJoaquimjj133/DIO-Projeto-Sudoku
cd sudoku-java

Passo 2: Compilar o Projeto
Passo 3: Executar o Jogo
java MainApp

Como Jogar
Iniciar Novo Jogo: Clique em "Novo Jogo" e selecione a dificuldade.

Preencher Células:

Clique em uma célula branca e digite um número (1-9).

Conflitos são destacados em rosa.

Usar Dica: Clique em "Dica (3)" para revelar um número correto.

Salvar/Carregar:

Salvar: Clique em "Salvar" para guardar o progresso.

Carregar: Clique em "Carregar" para retomar um jogo salvo.

Finalizar: Preencha todas as células corretamente para vencer!

Melhorias Futuras
Validação de Solução Única: Garantir que o tabuleiro gerado tenha apenas uma solução.

Mais Níveis de Dificuldade: Ex: "Expert" (60 células removidas).

Pausar/Retomar Tempo: Adicionar controle do contador.

Interface Mais Atraente: Melhorar cores e adicionar animações.

Contribuição
Contribuições são bem-vindas! Siga os passos:

Faça um fork do projeto.

Crie uma branch: git checkout -b minha-feature.

Commit suas mudanças: git commit -m 'Adiciona feature incrível'.

Push para a branch: git push origin minha-feature.

Abra um Pull Request.

