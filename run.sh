#!/bin/bash

#create jar
mvn clean install

#build image
docker build --build-arg FB_VERSION="1.0-SNAPSHOT" --build-arg=SPRING_PROFILE="local" -t ngnnath/fizzbuzzserver:1.0-SNAPSHOT .

# prometheus
sudo mkdir /etc/prometheus
sudo cp prometheus.yml /etc
sudo cp  docker-compose.xml /etc

