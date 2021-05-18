FROM openjdk:16

ADD target/avatar-api-1.2.jar app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app.jar"]

EXPOSE 8080
