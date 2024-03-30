FROM openjdk:8-jdk-alpine
ADD target/auth.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]