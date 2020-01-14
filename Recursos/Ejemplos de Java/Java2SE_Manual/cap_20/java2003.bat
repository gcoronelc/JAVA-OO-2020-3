@echo off
rem java2003.bat
rem A.Froufe, 26-Jun-1999

echo Borramos las clases generadas en anteriores ejecuciones
del java2003*.class

echo Compilamos el Servidor de los objetos remotos
javac java2003S.java

echo Compilamos el Cliente de los objetos remotos
javac java2003C.java

echo Creamos los ficheros "stub"y "skeleton"
rmic java2003Oa
rmic java2003Ob

echo Registramos los objetos
start rmiregistry

echo Lanzamos el servidor en una ventana MSDOS diferente
start java -Djava.security.policy=java.policy java2003S

echo Pausa hasta que el servidor este listo, luego...
pause

echo Ejecutamos el Cliente 
java -Djava.security.policy=java.policy java2003C
java -Djava.security.policy=java.policy java2003C

echo Cierra manualmente el servidor y el registro, por favor