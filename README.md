#FizzBuzz
___
**Description:**

Exercise: Write a simple fizz-buzz REST server. 

The original fizz-buzz consists in writing all numbers from 1 to 100, and just replacing all multiples of 3 by "fizz", all multiples of 5 by "buzz", and all multiples of 15 by "fizzbuzz". The output would look like this: "1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,...".

___
## Install

- clone the project
> git clone

- compile at the root of the project
> mvn clean install

A target directory will appear with the jar in it.

- Create the docker image.
  
 > docker build --build-arg FB_VERSION="1.0-SNAPSHOT" --build-arg=SPRING_PROFILE="local" -t  ngnnath/fizzbuzzserver:1.0-SNAPSHOT .

The docker image is now in local
> docker images ls
  
___
## Run docker compose to run Grafane Prometheus

copy prometheus.xml and docker-compose.xml in /etc/prometheus

> sudo mkdir /etc/prometheus
> sudo cp prometheus.xml /etc
> sudo cp  docker-compose.xml /etc
> cd /etc/prometheus

All of the command is in the script run.sh

- run docker-compose.yml

> docker-compose build -d

this docker-compose file create 3 containers :

- container FizzBuzz the server at port 8081
- container Prometheus for the metrics at port 9090
- container Grafana for the dashboard at port 3000

To see if it works I created a script that execute some requests and create metrics : /src/test/resources/launch_some_calls.sh
____
## Endpoints

- http://localhost:8081/fizzbuzz/dashboard :
  You can access to the dashboard by the following url

- http://localhost:8081/fizzbuzz/{int1}/{int2}/{limit}/{str1}/{str2}
  The request will return in a list of string
- http://localhost:8081/fizzbuzz/MostRequestCalled : will return the most request call with parameter if there are equally ranked requests it will display all
  of them.
  
- http://localhost:8081/swagger-ui.html : the documentation of the app.
___
#Metrics
- Prometheus
  http://localhost:9090/graph
  
- Grafana
  http://localhost:3000/d/fizzbuzz
  

When it's the first time that you're running the grafana, a login page will appear.

Please connect with login admin and password admin.
- you have to configure the grafana to th prometheus server by indicate the url.
  
- Import the dashboard by taking the one in the repository


