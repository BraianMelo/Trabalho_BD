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
all: compile copy_fxml copy_images

# Compila o programa
compile:
	@echo "\nCompilando..."
	$(JAVAC) $(JAVAFX_FLAGS) -cp $(SRC_DIR):$(MYSQL_JAR) -d $(BIN_DIR) \
		$(SRC_DIR)/application/*.java \
		$(SRC_DIR)/gui/*.java \
		$(SRC_DIR)/model/*.java \
		$(SRC_DIR)/model/enums/*.java \
		$(SRC_DIR)/model/dao/*.java \
		$(SRC_DIR)/util/*.java

# Copia o arquivo FXML para o diretório bin/gui
copy_fxml:
	@echo "\nCopiando os FXML's para a pasta bin/gui/..."
	mkdir -p $(BIN_DIR)/gui
	cp $(SRC_DIR)/gui/MenuView.fxml $(BIN_DIR)/gui/
	cp $(SRC_DIR)/gui/CryptidView.fxml $(BIN_DIR)/gui/
	
	mkdir -p $(BIN_DIR)/gui/styles
	cp $(SRC_DIR)/gui/styles/CrytidView.css $(BIN_DIR)/gui/styles/
	cp $(SRC_DIR)/gui/styles/MenuView.css $(BIN_DIR)/gui/styles/
	
copy_images:
	@echo "\nCopiando as imagens para a pasta bin/gui/images..."
	mkdir -p $(BIN_DIR)/gui/images
	cp $(SRC_DIR)/gui/images/* $(BIN_DIR)/gui/images/

# Executa o programa
execute:
	@echo "\nExecutando o programa..."
	$(JAVA) $(JAVAFX_FLAGS) -cp $(BIN_DIR):$(MYSQL_JAR) $(MAIN_CLASS)

# Limpa os arquivos compilados
clean:
	@echo "\nApagando a pasta bin/..."
	rm -rf $(BIN_DIR)/

# Recompila e executa o programa
recompile: clean compile copy_fxml copy_images execute
