# Nome do compilador
JAVAC = javac
JAVA = java

# Diretórios e arquivos
SRC_DIR = src
BIN_DIR = bin
MAIN_SRC = $(SRC_DIR)/app/Aplicacao.java
MAIN_CLASS = app.Aplicacao

# Caminho do JavaFX
JAVAFX_LIB = /home/braian/Documentos/Workspace/Libraries/openjfx-21.0.5_linux-x64_bin-sdk/javafx-sdk-21.0.5/lib
JAVAFX_FLAGS = --module-path $(JAVAFX_LIB) --add-modules javafx.controls,javafx.fxml

# Caminho do MySQL Connector
MYSQL_JAR = /home/braian/Documentos/Workspace/Libraries/mysql-connector-j_9.0.0-1ubuntu22.04_all/usr/share/java/mysql-connector-java-9.0.0.jar


# Alvo padrão
all: compile copy_resources

# Compila o programa
compile:
	@echo "\nCompilando..."
	$(JAVAC) $(JAVAFX_FLAGS) -cp $(SRC_DIR):$(MYSQL_JAR) -d $(BIN_DIR) \
		$(SRC_DIR)/app/*.java \
		$(SRC_DIR)/modelo/enums/*.java \
		$(SRC_DIR)/modelo/*.java \
		$(SRC_DIR)/persistencia/*.java \
		$(SRC_DIR)/controle/*.java \
		$(SRC_DIR)/utilitario/*.java

# Copia os fxmls, css e as imagens para a pasta bin
copy_resources:
	@echo "\nCopiando recursos (FXML, imagens e stylesheets) para a pasta bin/visao..."
	mkdir -p $(BIN_DIR)/visao $(BIN_DIR)/visao/imagens $(BIN_DIR)/visao/estilos
	cp -r $(SRC_DIR)/visao/*.fxml $(BIN_DIR)/visao/
	cp -r $(SRC_DIR)/visao/imagens/* $(BIN_DIR)/visao/imagens/
	cp -r $(SRC_DIR)/visao/estilos/*.css $(BIN_DIR)/visao/estilos/


# Executa o programa
execute:
	@echo "\nExecutando o programa..."
	$(JAVA) $(JAVAFX_FLAGS) -cp $(BIN_DIR):$(MYSQL_JAR) $(MAIN_CLASS)

# Limpa os arquivos compilados
clean:
	@echo "\nApagando a pasta bin/..."
	rm -rf $(BIN_DIR)/

# Recompila e executa o programa
recompile: clean compile copy_resources execute
