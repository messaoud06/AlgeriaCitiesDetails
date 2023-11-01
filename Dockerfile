FROM openjdk:17-jdk-oracle
MAINTAINER dz.web
COPY target/AlgeriaCitiesDetails-1.0.0.jar AlgeriaCitiesDetails-1.0.0.jar
ENTRYPOINT ["java","-jar","/AlgeriaCitiesDetails-1.0.0.jar"]