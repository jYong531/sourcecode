FROM 22af113106fa
VOLUME /tmp
ADD DockerLearning.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]