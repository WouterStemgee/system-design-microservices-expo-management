FROM openjdk:8
COPY ./target/event-reservation-0.0.1-SNAPSHOT.jar /usr/src/myapp/event-reservation-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "event-reservation-0.0.1-SNAPSHOT.jar"]