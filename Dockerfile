FROM openjdk:8
COPY ./target/tracking-0.0.1-SNAPSHOT.jar /app/tracking-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "tracking-0.0.1-SNAPSHOT.jar"]