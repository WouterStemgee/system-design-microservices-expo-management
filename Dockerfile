FROM openjdk:8
COPY ./target/parking-0.0.1-SNAPSHOT.jar /app/parking-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "parking-0.0.1-SNAPSHOT.jar"]