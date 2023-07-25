FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ./target/dicovery-server-1.0-SNAPSHOT.jar dicovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","dicovery-server.jar"]