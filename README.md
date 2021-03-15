# task001
Simple app

##Build

configure database users in dbDocker/sql/setup-script.sql <br>
create application.properties files using application.properties.sample

Use 
``
mvn exec:exec@docker-build
``
to create local docker images.
## Run

Create a docker volume for database docker <br>
``
docker volume create psqlDb
``

Run db docker
````
docker run -d \
    -p 5432:5432 \
    --name task001-db \
    -e POSTGRES_PASSWORD=password \
    -v psqlDb:/var/lib/postgresql/data \
    task001.database:0.9.0
````

Run customers web service
```
docker run -d -p 8081:8080 --add-host=dbhost:<dbhost> task001.customers:0.9.0 
```
Run products web service
```
docker run -d -p 8083:8080 --add-host=dbhost:<dbhost> task001.products:0.9.0 
```
run the main web service
````
    docker run -d -p 8082:8080 \
    --add-host=dbhost:192.168.0.1 \
    --add-host=customershost:192.168.0.1 \
    --add-host=productshost:192.168.0.1 \
    task001.credits:0.9.0 
````
##Usage

####Curl

get all credits<br>
``curl --location --request GET 'http://127.0.0.1:8082/api/credits'``

Creat new credit<br>
````
curl --location --request POST 'http://127.0.0.1:8082/api/credits' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "name",
 "product": {
     "value": 123456
 },
 "customer" : {
     "firstname": "firstname",
     "surname": "surname",
     "pesel": "00242244732"
 }
}'
````

####Postman
You can import ``task001.postman_collection.json`` into postman.