# Spring Boot API con Docker

Este proyecto es una API RESTful creada con Spring Boot en Java 17. A continuación, se detallan los pasos para clonar el proyecto, construirlo y ejecutarlo en un contenedor Docker.

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina local:

- [Git]
- [Docker]
- [Maven]

## Clonar el proyecto

Clona el repositorio desde GitHub

## Generar .jar:
1- Navega al directorio del proyecto.
2- Ejecuta el siguiente comando para construir el archivo JAR:
   
   ./mvnw clean package

## Construye la imagen Docker con el siguiente comando:
   
   docker build -t demo-ingenia:latest .

## Ejecuta el contenedor Docker usando la imagen que acabas de construir:

  docker run -p 8080:8080 demo-ingenia

# Endpoints para probar la API

## Crea una estación:

curl --location 'http://localhost:8080/stations' \
--header 'Content-Type: application/json' \
--data '{
    "station_id": 1,
    "name": "A"
}'

## Crea un path:

curl --location 'http://localhost:8080/paths' \
--header 'Content-Type: application/json' \
--data '{
    "path_id": 1,
    "cost": 20.0,
    "source_id": 1,
    "destination_id": 2
}'

## Busca la trayectoria más económica entre dos estaciones

curl --location 'http://localhost:8080/paths/3/1'
