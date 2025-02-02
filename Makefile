# Nome do compilador
JAVAC = javac
JAVA = java

# Diretórios e arquivos
SRC_DIR = src
BIN_DIR = bin
MAIN_SRC = $(SRC_DIR)/application/Main.java
MAIN_CLASS = application.Main

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
		$(SRC_DIR)/application/*.java \
		$(SRC_DIR)/model/enums/*.java \
		$(SRC_DIR)/model/*.java \
		$(SRC_DIR)/persistence/*.java \
		$(SRC_DIR)/controller/*.java \
		$(SRC_DIR)/util/*.java

# Copia os fxmls, css e as imagens para a pasta bin
copy_resources:
	@echo "\nCopiando recursos (FXML, imagens e stylesheets) para a pasta bin/view..."
	mkdir -p $(BIN_DIR)/view $(BIN_DIR)/view/images $(BIN_DIR)/view/styles
	cp -r $(SRC_DIR)/view/*.fxml $(BIN_DIR)/view/
	cp -r $(SRC_DIR)/view/images/* $(BIN_DIR)/view/images/
	cp -r $(SRC_DIR)/view/styles/*.css $(BIN_DIR)/view/styles/


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
