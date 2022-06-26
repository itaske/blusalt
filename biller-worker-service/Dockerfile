FROM openjdk:8-jdk-alpine
EXPOSE 9090
ARG JAR_FILE=target/biller-worker-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} biller-worker-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/biller-worker-service-0.0.1-SNAPSHOT.jar"]