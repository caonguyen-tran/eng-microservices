FROM maven:3.9.9-amazoncorretto-21 as build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests


FROM amazoncorretto:21.0.4

WORKDIR /app
COPY --from=build /app/target/*.jar api-gateway.jar

ENTRYPOINT ["java", "-jar", "api-gateway.jar"]