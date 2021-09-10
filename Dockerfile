FROM openjdk:11
MAINTAINER com.shiladitya
COPY target/corona-tracker-0.0.1-SNAPSHOT.jar corona-tracker-1.0.0.jar
ENTRYPOINT ["java","-jar","/corona-tracker-1.0.0.jar"]
