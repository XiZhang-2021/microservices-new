FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ./target/identity-service-1.0-SNAPSHOT.jar identity-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","identity-service.jar"]