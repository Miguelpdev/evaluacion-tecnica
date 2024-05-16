FROM eclipse-temurin:17-jdk-focal AS BASE

WORKDIR /app

COPY target/*-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]