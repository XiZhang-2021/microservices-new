FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ./target/api-gateway-1.0-SNAPSHOT.jar api-gateway.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","api-gateway.jar"]
