FROM gradle:8-jdk17-alpine AS build
COPY --chown=gradle:gradle . ./src
WORKDIR /src
RUN gradle build --no-daemon

FROM openjdk:17-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]