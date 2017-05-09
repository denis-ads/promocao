#!/bin/bash  
docker-compose down
mvn package
docker-compose build
docker-compose up
