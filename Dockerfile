FROM openjdk:8
ADD target/person-service.jar person-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "person-service.jar"]
