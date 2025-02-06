# Projeto: Sistema de Gerenciamento de Banco de Dados - Criptozoologia

Este projeto consiste em um sistema de gerenciamento de banco de dados com interface gráfica desenvolvido em Java utilizando JavaFX. Ele foi projetado para gerenciar informações relacionadas ao tema de criptozoologia.

## Estrutura do Projeto

```
Trabalho_BD/
├── database/         # Scripts do banco de dados
├── bin/              # Arquivos compilados
├── src/              # Código-fonte do projeto
│   ├── app/          # Ponto de entrada da aplicação
│   ├── controle/     # Controladores da interface
│   ├── visao/        # Interface gráfica
│   │   ├── imagens/  # Ícones e imagens
│   │   └── estilos/  # Estilos visuais
│   ├── modelo/       # Dados e regras de negócio
│   │   └── enums/    # Enumerações
│   ├── persistencia/ # Acesso a dados
│   └── util/         # Utilitários gerais
├── Makefile          # Automação de compilação e execução
└── README.md         # Documentação do projeto

```

## Requisitos

- **Java Development Kit (JDK)**: Versão 21 ou superior.
- **JavaFX SDK**: Versão compatível com o JDK instalado.

## Configuração do Ambiente

1. **Instalar o JDK**: Certifique-se de que o JDK está instalado e configurado corretamente no PATH.
2. **Instalar o JavaFX SDK**: Baixe o JavaFX SDK e extraia os arquivos em um diretório de sua preferência.
3. **Configurar o Projeto**:
   - Atualize o caminho do JavaFX no `Makefile`:
     ```
     JAVAFX_LIB = /caminho/para/javafx-sdk/lib
     ```

## Como Executar

### Compilar o Projeto

Use o comando abaixo para compilar o projeto:
```bash
make
```

### Executar o Projeto

Após a compilação, execute o projeto com o comando:
```bash
make run
```

### Limpar Arquivos Compilados

Para remover os arquivos compilados, utilize:
```bash
make clean
```

## Autores

- **GitHub do Braian**: [https://github.com/BraianMelo](https://github.com/BraianMelo)
- **GitHub do Gustavo**: [https://github.com/GustavoH-C](https://github.com/GustavoH-C)
- **GitHub do Yuri**: [https://github.com/YuriDrumond](https://github.com/YuriDrumond)
- **Repositório do Projeto**: [https://github.com/BraianMelo/Trabalho_BD](https://github.com/BraianMelo/Trabalho_BD)
