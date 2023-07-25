FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ./target/product-service-1.0-SNAPSHOT.jar product-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","product-service.jar"]