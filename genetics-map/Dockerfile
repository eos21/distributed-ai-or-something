FROM computes/lein:latest as build

WORKDIR /workdir
COPY . .
RUN lein uberjar

# Entrypoint
FROM openjdk:alpine

COPY --from=build /workdir/target/uberjar/*standalone.jar /application.jar
ENTRYPOINT ["java", "-jar", "./application.jar"]
