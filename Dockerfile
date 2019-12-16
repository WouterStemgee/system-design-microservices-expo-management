FROM openjdk:8
COPY ./target/badge-0.0.1-SNAPSHOT.jar /app/badge-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "badge-0.0.1-SNAPSHOT.jar"]