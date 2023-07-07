cls
@echo off
REM compilation de FW
cd Framework\build

javac -d . ..\src\*.java

REM exportation en jar

jar -cvf ..\..\fw.jar .
cd ..\..\

