FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/customer-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} customer-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customer-service-0.0.1-SNAPSHOT.jar"]