FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/event-reservation-0.0.1-SNAPSHOT.jar /usr/src/myapp/event-reservation-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "event-reservation-0.0.1-SNAPSHOT.jar"]