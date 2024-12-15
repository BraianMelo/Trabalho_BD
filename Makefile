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

# Caminho do MySQL Connector/J
MYSQL_JAR = /home/braian/Documentos/Workspace/Libraries/mysql-connector-j_9.0.0-1ubuntu22.04_all/usr/share/java/mysql-connector-java-9.0.0.jar


# Alvo padrão
all: compile copy_fxml

# Compila o programa
compile:
	# Compilando o programa...
	$(JAVAC) $(JAVAFX_FLAGS) -cp $(SRC_DIR):$(MYSQL_JAR) -d $(BIN_DIR) \
		$(SRC_DIR)/application/*.java \
		$(SRC_DIR)/gui/*.java \
		$(SRC_DIR)/model/*.java \
		$(SRC_DIR)/model/enums/*.java \
		$(SRC_DIR)/model/dao/*.java

# Copia o arquivo FXML para o diretório bin/gui
copy_fxml:
	# Verifica se o diretório bin/gui existe e cria se necessário
	mkdir -p $(BIN_DIR)/gui
	cp $(SRC_DIR)/gui/MenuView.fxml $(BIN_DIR)/gui/

# Executa o programa
run: compile copy_fxml
	clear
	# Rodando o programa...
	$(JAVA) $(JAVAFX_FLAGS) -cp $(BIN_DIR):$(MYSQL_JAR) $(MAIN_CLASS)

# Limpa os arquivos compilados
clean:
	find $(BIN_DIR) -name "*.class" -delete
	rm -rf $(BIN_DIR)/gui/MenuView.fxml

# Recompila e executa o programa
recompile: clean compile run
