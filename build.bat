@echo off
REM compilation de FW
cd Framework\build
javac -d . ..\src\Mapping.java
javac -d . ..\src\ModelView.java
javac -d . ..\src\Urls.java
javac -d . ..\src\Utils.java
javac -d . ..\src\FrontServlet.java

REM exportation en jar

jar -cvf ..\..\fw.jar .
cd ..\..\

REM copie vers le projet de test
copy fw.jar Test-Framework\WEB-INF\lib

REM compilation du projet de test
cd Test-Framework\WEB-INF\classes
set CLASSPATH=%CLASSPATH%;E:\S4\
\M_Naina\Framework_2m_fetch\Renato-Framework\fw.jar
javac -d . ../../src/*.java

Rem vers Test-Framework
cd ../../
REM creation de l'archive war
jar -cvf ../TFW.war .

Rem retour sur le repertoire principal
cd ../
REM copie vers le deploiment
copy TFW.war E:\app\JSP\apache-tomcat-8.5.82\webapps