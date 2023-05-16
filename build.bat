cls
@echo off
REM compilation de FW
cd Framework\build
REM javac -d . ..\src\Mapping.java
REM javac -d . ..\src\ModelView.java
REM javac -d . ..\src\Urls.java
REM javac -d . ..\src\Utils.java
REM javac -d . ..\src\FrontServlet.java

javac -parameters -d . ..\src\*.java

REM exportation en jar

jar -cvf ..\..\fw.jar .
cd ..\..\

REM copie vers le projet de test
copy fw.jar Test-Framework\WEB-INF\lib

REM compilation du projet de test
cd Test-Framework\WEB-INF\classes
set CLASSPATH=%CLASSPATH%;E:\S4\projets\M_Naina\Framework_2m_fetch\Renato-Framework\fw.jar
echo "AFFICHAGE DE CLASSPATH: "
echo %CLASSPATH%
javac -parameters -d . ../../src/*.java

Rem vers Test-Framework
cd ../../
REM creation de l'archive war
jar -cvf ../TFW.war .

Rem retour sur le repertoire principal
cd ../
REM copie vers le deploiment
copy TFW.war E:\app\JSP\apache-tomcat-8.5.82\webapps
