# we will use openjdk 8 with alpine as it is a very small linux distro
FROM openjdk:8-jre-alpine3.9

EXPOSE 8081
# copy the packaged jar file into our docker image
COPY target/FizzBuzzServer-1.0-SNAPSHOT.jar /FizzBuzzServer-1.0-SNAPSHOT.jar

# set the startup command to execute the jar
CMD ["java", "-jar","-Dspring.profiles.active=local", "/FizzBuzzServer-1.0-SNAPSHOT.jar"]