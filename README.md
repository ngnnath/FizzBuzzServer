#FizzBuzz
___
**Description:**

Exercise: Write a simple fizz-buzz REST server. 

The original fizz-buzz consists in writing all numbers from 1 to 100, and just replacing all multiples of 3 by "fizz", all multiples of 5 by "buzz", and all multiples of 15 by "fizzbuzz". The output would look like this: "1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,...".


## Summary
1. [Build the Docker image](#dockerImage)
2. [Run Docker-compose](#docker-compose)
3. [Show the metrics in grafana](#metrics)
4. [Endpoints of the service](#endpoints)

___

## Build the Docker Image 

<a name="dockerImage"><a>

- clone the project
```
  git clone
```
- compile at the root of the project
```
mvn clean install
```
A target directory will appear with the jar in it.

- Create the docker image.

```
docker build --build-arg FB_VERSION="1.0-SNAPSHOT" -t  ngnnath/fizzbuzzserver:1.0-SNAPSHOT .
```
The docker image is now in local
```
docker image ls
```  
___
## Run docker-compose to run Grafana Prometheus

<a name="docker-compose"><a>

copy prometheus.xml and docker-compose.xml in /etc/prometheus

```
sudo mkdir /etc/prometheus
sudo cp prometheus.yml /etc/prometheus
sudo cp  docker-compose.xml /etc/prometheus
sudo cp  .env /etc/prometheus
cd /etc/prometheus
```
All of the command is in the script run.sh

- You can configure the ports of the different services by modifying the .env file
  
- run docker-compose.yml
```
docker-compose up
```
this docker-compose file create 3 containers :

- container FizzBuzz the server at port 8081
- container Prometheus for the metrics at port 9090
- container Grafana for the dashboard at port 3000


To see if it works I created a script that execute some requests and create metrics.

____
#Show the metrics in grafana

<a name="metrics"><a>

Check if the Prometheus get the metric of fizz-buzz service 
- localhost:9090/targets

Open grafana

- http://localhost:3000

When it's the first time that you're running the grafana, a login page will appear.
Please connect with login admin and password admin.

- Set the prometheus Server URL (URL: "http://localhost:9090/" and Acccess to "Browser") 

- Import the dashboard by taking the grafana Json in the repository

___
## Endpoints of the service

<a name="endpoints"><a>

- http://localhost:8081/fizzbuzz/grafana :
  You can access to the dashboard by the following url

- http://localhost:8081/fizzbuzz/{int1}/{int2}/{limit}/{str1}/{str2}
  The request will return in a list of string
- http://localhost:8081/fizzbuzz/MostRequestCalled : will return the most request call with parameter if there are equally ranked requests it will display all
  of them.
  
- http://localhost:8081/swagger-ui.html : the documentation of the app.
___



