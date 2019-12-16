FROM openjdk:8
COPY ./target/ticket-0.0.1-SNAPSHOT.jar /app/ticket-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "ticket-0.0.1-SNAPSHOT.jar"]