FROM openjdk:17-jdk-alpine
MAINTAINER Mohammed_Zishan
COPY target/patient-0.0.1-SNAPSHOT.jar patient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/patient-0.0.1-SNAPSHOT.jar"]