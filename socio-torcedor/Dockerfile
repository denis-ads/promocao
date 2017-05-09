FROM maven:latest
COPY . /usr/src/socio-torcedor
WORKDIR /usr/src/socio-torcedor
RUN mvn package -DskipTests

FROM java:8-jdk-alpine
RUN mkdir -p /app/
COPY --from=1 /usr/src/socio-torcedor/target/socio-torcedor-swarm.jar /app
CMD ["java", "-jar","/app/socio-torcedor-swarm.jar",]
