FROM eclipse-temurin:11

LABEL mentainer="ngonimug.@gmail.com"

WORKDIR /app

COPY target/drones-web-services-0.0.1-SNAPSHOT.jar /app/drones-webservices.jar

ENTRYPOINT ["java", "-jar", "drones-webservices.jar"]