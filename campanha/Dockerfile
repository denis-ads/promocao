FROM maven:latest
COPY . /usr/src/campanha
WORKDIR /usr/src/campanha
RUN mvn package -DskipTests

FROM java:8-jdk-alpine
RUN mkdir -p /app/
COPY --from=1 /usr/src/campanha/target/campanha-swarm.jar /app
CMD ["java", "-jar","/app/campanha-swarm.jar",]
