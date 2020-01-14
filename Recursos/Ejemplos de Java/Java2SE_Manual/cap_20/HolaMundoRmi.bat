@echo off
rem File HolaMundoRmi.bat
rem A.Froufe, 26-Jun-1999

echo Borramos las clases generadas en anteriores ejecuciones
del HolaMundoRmi*.class

echo Compilamos el Servidor del objeto remoto
javac HolaMundoRmiS.java

echo Compilamos el Cliente del objeto remoto
javac HolaMundoRmiC.java

echo Creamos los ficheros "stub"y "skeleton"
rmic HolaMundoRmiO

echo Registramos el objeto remoto
start rmiregistry
  
echo Lanzamos el servidor en una ventana MSDOS diferente
start java -Djava.security.policy=java.policy HolaMundoRmiS

echo Pausa hasta que el servidor este listo, luego...
pause

echo Ejecutamos el cliente dos veces para demostrar su uso
java -Djava.security.policy=java.policy HolaMundoRmiC
java -Djava.security.policy=java.policy HolaMundoRmiC

echo Cierra manualmente el servidor y el registro, por favor