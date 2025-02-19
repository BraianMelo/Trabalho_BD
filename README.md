# Sistema de Gerenciamento de Banco de Dados - Criptozoologia

Este projeto é um sistema de gerenciamento de banco de dados com interface gráfica desenvolvido em **Java** utilizando **JavaFX** e **JDBC**. Ele permite a administração de informações relacionadas à **criptozoologia**, incluindo registros de avistamentos, pesquisadores, testemunhas e criptídeos confirmados.

## 📁 Estrutura do Projeto

```
Trabalho_BD/
├── database/          # Scripts do banco de dados
├── bin/               # Arquivos compilados
├── src/               # Código-fonte do projeto
│   ├── app/           # Ponto de entrada da aplicação
│   ├── controle/      # Controladores da interface
│   ├── visao/         # Interface gráfica
│   │   ├── imagens/   # Ícones e imagens
│   │   └── estilos/   # Estilos visuais
│   ├── modelo/        # Dados e regras de negócio
│   │   └── enums/     # Enumerações
│   └──  persistencia/ # Acesso a dados (JDBC, DAOs)
├── Makefile           # Automação de compilação e execução
├── README.md          # Documentação do projeto
└── .gitignore         # Arquivos ignorados pelo Git
```

## 🛠️ Tecnologias Utilizadas

- **Java (JDK 21+)**
- **JavaFX** (Interface gráfica)
- **JDBC** (Conexão com o banco de dados)
- **MySQL** (Banco de dados relacional)
- **Makefile** (Automação de compilação e execução)

## 🚀 Configuração do Ambiente

1. **Instalar o JDK**: Certifique-se de que o JDK 21 ou superior está instalado e configurado corretamente no PATH.
2. **Instalar o JavaFX SDK**: Baixe o JavaFX SDK e extraia os arquivos para um diretório de sua preferência.
3. **Configurar o caminho do JavaFX no `Makefile`**:
   ```make
   JAVAFX_LIB = /caminho/para/javafx-sdk/lib
   ```
4. **Configurar o Banco de Dados**:
   - Certifique-se de que o **MySQL** está instalado e rodando.
   - Crie o banco de dados utilizando os scripts na pasta `database/`.
   - Configure a conexão no `database/BD.properties`.

## ▶️ Como Executar

### 📌 Compilar o Projeto
Execute o seguinte comando no terminal:
```bash
make
```

### 📌 Executar o Projeto
Após a compilação, rode:
```bash
make execute
```

### 📌 Limpar Arquivos Compilados
Para remover os arquivos gerados na compilação:
```bash
make clean
```

## 👥 Autores

- **Braian Melo**: [GitHub](https://github.com/BraianMelo)
- **Gustavo Henrique**: [GitHub](https://github.com/GustavoH-C)
- **Yuri Drumond**: [GitHub](https://github.com/YuriDrumond)
- **Repositório do Projeto**: [GitHub](https://github.com/BraianMelo/Trabalho_BD)

---
📌 *Este projeto foi desenvolvido para fins acadêmicos e de aprendizado.* 🎓
