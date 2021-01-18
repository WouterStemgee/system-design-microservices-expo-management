FROM openjdk:8
COPY ./target/security-0.0.1-SNAPSHOT.jar /app/security-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "security-0.0.1-SNAPSHOT.jar"]