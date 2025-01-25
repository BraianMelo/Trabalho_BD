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
all: compile copy_fxml copy_images copy_stylesheets

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

# TODO: 'gui/' virar 'view/'

# Copia o arquivo FXML para o diretório bin/view
copy_fxml:
	@echo "\nCopiando os FXML's para a pasta bin/view/..."
	mkdir -p $(BIN_DIR)/view
	cp $(SRC_DIR)/view/MenuView.fxml $(BIN_DIR)/view/
	cp $(SRC_DIR)/view/CryptidView.fxml $(BIN_DIR)/view/
	cp $(SRC_DIR)/view/CryptidPane.fxml $(BIN_DIR)/view/
	cp $(SRC_DIR)/view/EditCryptidPane.fxml $(BIN_DIR)/view/
	
copy_images:
	@echo "\nCopiando as imagens para a pasta bin/view/images..."
	mkdir -p $(BIN_DIR)/view/images
	cp $(SRC_DIR)/view/images/* $(BIN_DIR)/view/images/
	
copy_stylesheets:
	@echo "\nCopiando os stylesheets para a pasta bin/view/styles/..."
	mkdir -p $(BIN_DIR)/view/styles
	cp $(SRC_DIR)/view/styles/CrytidView.css $(BIN_DIR)/view/styles/
	cp $(SRC_DIR)/view/styles/MenuView.css $(BIN_DIR)/view/styles/
	cp $(SRC_DIR)/view/styles/EditCryptidPane.css $(BIN_DIR)/view/styles/
	
	mkdir -p $(BIN_DIR)/view/styles
	cp $(SRC_DIR)/view/styles/CryptidPane.css $(BIN_DIR)/view/styles/

# Executa o programa
execute:
	@echo "\nExecutando o programa..."
	$(JAVA) $(JAVAFX_FLAGS) -cp $(BIN_DIR):$(MYSQL_JAR) $(MAIN_CLASS)

# Limpa os arquivos compilados
clean:
	@echo "\nApagando a pasta bin/..."
	rm -rf $(BIN_DIR)/

# Recompila e executa o programa
recompile: clean compile copy_fxml copy_images copy_stylesheets execute
