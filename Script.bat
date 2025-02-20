@echo off
setlocal

:: Configuração dos diretórios
set SRC_DIR=src
set BIN_DIR=bin
set LIBS_DIR=libs
set MAIN_CLASS=app.Aplicacao

:: Configuração dos JARs
set JAVAFX_LIB=%LIBS_DIR%\javafx-sdk-21.0.5/lib
set MYSQL_JAR=%LIBS_DIR%\mysql-connector-j-9.0.0.jar

:: Configuração do Java
set JAVAC=javac
set JAVA=java
set JAVAFX_FLAGS=--module-path "%JAVAFX_LIB%" --add-modules javafx.controls,javafx.fxml

:: Criando pasta bin, se necessário
if not exist %BIN_DIR% mkdir %BIN_DIR%

:: Compilando
echo.
echo Compilando...
%JAVAC% %JAVAFX_FLAGS% -cp "%SRC_DIR%;%MYSQL_JAR%" -d %BIN_DIR% ^
    %SRC_DIR%\app\*.java ^
    %SRC_DIR%\modelo\enums\*.java ^
    %SRC_DIR%\modelo\*.java ^
    %SRC_DIR%\persistencia\*.java ^
    %SRC_DIR%\controle\*.java

:: Copiando recursos (FXML, imagens e CSS)
echo.
echo Copiando recursos...
if not exist %BIN_DIR%\visao mkdir %BIN_DIR%\visao
if not exist %BIN_DIR%\visao\imagens mkdir %BIN_DIR%\visao\imagens
if not exist %BIN_DIR%\visao\estilos mkdir %BIN_DIR%\visao\estilos

xcopy /E /Y %SRC_DIR%\visao\*.fxml %BIN_DIR%\visao\
xcopy /E /Y %SRC_DIR%\visao\imagens %BIN_DIR%\visao\imagens\
xcopy /E /Y %SRC_DIR%\visao\estilos %BIN_DIR%\visao\estilos\

:: Executando
echo.
echo Executando o programa...
%JAVA% %JAVAFX_FLAGS% -cp "%BIN_DIR%;%MYSQL_JAR%" %MAIN_CLASS%

endlocal
