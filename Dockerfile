FROM openjdk:8
COPY ./target/food-and-drinks-0.0.1-SNAPSHOT.jar /app/food-and-drinks-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "food-and-drinks-0.0.1-SNAPSHOT.jar"]