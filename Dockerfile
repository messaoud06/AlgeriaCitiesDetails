FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/algeriacitiesdetails-1.1.0.jar algeriacitiesdetails-1.1.0.jar
ENTRYPOINT ["java","-jar","/algeriacitiesdetails-1.1.0.jar"]
EXPOSE 8081