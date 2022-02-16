FROM openjdk:11.0-oracle
ARG JAR_FILE=build/libs/customer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} customer.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/customer.jar"]