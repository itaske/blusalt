FROM openjdk:8-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=target/biller-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} biller-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/biller-service-0.0.1-SNAPSHOT.jar"]