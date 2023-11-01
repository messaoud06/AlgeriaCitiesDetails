FROM openjdk:17-jdk-oracle
MAINTAINER dz.web
COPY AlgeriaCitiesDetails-1.0.0.jar AlgeriaCitiesDetails-1.0.0.jar
ENTRYPOINT ["java","-jar","/AlgeriaCitiesDetails-1.0.0.jar"]