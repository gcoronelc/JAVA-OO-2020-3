@echo off
rem java2001.bat
rem A.Froufe, 26-Jun-1999

echo Borramos las clases generadas en anteriores ejecuciones
del java2001*.class

echo Compilamos el Servidor objetos remotos
javac java2001S.java

echo Compilamos el Cliente del objeto remoto
javac java2001C.java

echo Creamos los ficheros "stub"y "skeleton"
rmic java2001O

echo Registramos el objeto remoto
start rmiregistry
  
echo Lanzamos el servidor en una ventana MSDOS diferente
start java -Djava.security.policy=java.policy java2001S

echo Pausa hasta que el servidor este listo, luego...
pause

echo Ejecutamos el cliente dos veces
java -Djava.security.policy=java.policy java2001C
java -Djava.security.policy=java.policy java2001C

echo Cierra manualmente el servidor y el registro, por favor