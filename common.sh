#!/bin/bash

git pull





docker rm -f $(docker ps -aq) && rm -rf /opt/docker/mysql_server && docker rmi spring-boot/spring-start-template:latest

mvn clean package -Pprod -Dmaven.test.skip=true docker:build

docker-compose up -d

docker volume rm $(docker volume ls -q)


docker rm -f vue_cms_front &&  docker rmi vue_cms_front:v1