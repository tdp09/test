cd .\eIncidenciesClient\src\Client
SET CLASSPATH=%CLASSPATH%;..\;

javac *.java

cd ..

java Client.ApplicationMainWindow 
