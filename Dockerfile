FROM openjdk:17
VOLUME /tmp
#EXPOSE 8081
ARG JAR_FILE=target/api-userpost-management-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]