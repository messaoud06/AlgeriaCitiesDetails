FROM eclipse-temurin:17-jdk-alpine

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
ENTRYPOINT ["java","-jar","target/algeriacitiesdetails-1.1.0.jar"]

#VOLUME /tmp
#COPY target/algeriacitiesdetails-1.1.0.jar algeriacitiesdetails-1.1.0.jar
#ENTRYPOINT ["java","-jar","/algeriacitiesdetails-1.1.0.jar"]
EXPOSE 8081