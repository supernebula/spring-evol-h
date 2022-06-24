FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=evol-web-manage-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} evol-web-manage-1.0-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "evol-web-manage-1.0-SNAPSHOT.jar"]