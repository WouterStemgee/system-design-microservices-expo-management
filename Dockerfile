FROM openjdk:8
COPY ./target/multimedia-0.0.1-SNAPSHOT.jar /app/multimedia-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "multimedia-0.0.1-SNAPSHOT.jar"]
