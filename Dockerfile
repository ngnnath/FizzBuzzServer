FROM openjdk:8-jre-alpine3.9

ARG FB_VERSION
ENV FB_VERSION=${FB_VERSION}

ARG SPRING_PROFILE
ENV SPRING_PROFILE=${SPRING_PROFILE}

ARG FB_PORT
ENV FB_PORT=${FB_PORT}
EXPOSE ${FB_PORT}

ENV APP_HOME /appli
ENV APP_CONF_HOME /appli/conf

RUN mkdir -p ${APP_HOME}  \
    && mkdir -p ${APP_CONF_HOME}

# copy the packaged jar file into our docker image
COPY target/FizzBuzzServer-${FB_VERSION}.jar ${APP_HOME}/FizzBuzzServer-${FB_VERSION}.jar
COPY src/main/resources/* ${APP_CONF_HOME}/

RUN chmod +x ${APP_HOME}/FizzBuzzServer-${FB_VERSION}.jar

ENTRYPOINT exec java \
    -Dspring.profiles.active=${SPRING_PROFILE}\
     -Dspring.config=${APP_CONF_HOME}\
     -Dserver.port=${FB_PORT}\
    -jar ${APP_HOME}/FizzBuzzServer-${FB_VERSION}.jar