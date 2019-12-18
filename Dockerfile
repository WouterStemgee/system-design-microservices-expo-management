FROM openjdk:8
COPY ./target/hall-management-0.0.1-SNAPSHOT.jar /app/hall-management-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "hall-management-0.0.1-SNAPSHOT.jar"]