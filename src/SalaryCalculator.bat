@echo off

rem Get the current directory
set "currentDir=%~dp0"

rem Get the name of the Java file without extension
set "javaFile=./Main.java"
for %%F in ("%javaFile%") do (
    set "name=%%~nF"
)

rem Compile the Java file
javac "%currentDir%%javaFile%"

rem Check if the compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

echo Compilation successful.

rem Run the compiled Java program
java "Main.java"

pause
