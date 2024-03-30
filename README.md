
RUN IN DOCKER
---------------------------------------------
docker build --tag=patient-management:latest .
docker run -p8887:8080 patient-management:latest

PACKAGE TO JAR USING MAVEN
--------------------------------------------
mvn package

RUN APPLICATION WITHOUT DOCKER
--------------------------------------------
cd target
java -jar patient-0.0.1-SNAPSHOT.jar
