@echo off
setlocal

echo Cleaning up old class files...
del /S /Q *.class

echo Generating source.txt file...
del source.txt 2>nul
(for /R %%f in (*.java) do @echo %%f) > source.txt

echo Compiling Java source files...
javac @source.txt

if errorlevel 1 (
    echo Compilation failed.
    exit /b 1
)

echo Creating jar package...
del MarioAI.jar 2>nul
jar cf Mario-AI-Framework.jar -C . .

echo Done!
endlocal
