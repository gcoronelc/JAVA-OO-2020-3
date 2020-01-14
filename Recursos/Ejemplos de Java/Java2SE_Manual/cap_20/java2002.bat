@echo off
rem java2002.bat
rem A.Froufe, 26-Jun-1999

echo Borramos las clases generadas en anteriores ejecuciones
del java2002*.class

echo Compilamos el Servidor de los objetos remotos
javac java2002S.java

echo Compilamos el Cliente de los objetos remotos
javac java2002C.java

echo Creamos los ficheros "stub"y "skeleton"
rmic java2002Oa
rmic java2002Ob

echo Lanzamos el servidor en una ventana MSDOS diferente
start java -Djava.security.policy=java.policy java2002S

echo Pausa hasta que el servidor este listo, luego...
pause

echo Ejecutamos el Cliente 
java -Djava.security.policy=java.policy java2002C

echo Cierra manualmente el servidor y el registro, por favor