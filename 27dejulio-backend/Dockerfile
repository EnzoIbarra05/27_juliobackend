FROM openjdk:22-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app_27dejulio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_27dejulio.jar"]