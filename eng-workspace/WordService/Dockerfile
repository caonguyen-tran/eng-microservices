FROM maven:3.9.9-amazoncorretto-21 as build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests


FROM amazoncorretto:21.0.4

WORKDIR /app
COPY --from=build /app/target/*.jar word_service.jar

ENTRYPOINT ["java", "-jar", "word_service.jar"]