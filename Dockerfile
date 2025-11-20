FROM openjdk:17
EXPOSE 8080
ADD target/ShareIt.jar ShareIt.jar
ENTRYPOINT ["java" , "-jar","/ShareIt.jar"]
