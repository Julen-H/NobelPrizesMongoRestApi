# NobelPrizesMongoRestApi by Julen Herrero

This project has been developed for the Data Access subject of the second course of Development of Multiplatform Applications in Uni Eibar. The main task is to develop a Rest Api that access a MongoDB database and to learn how a Rest Api can fit in a web application. In this case a JSON dataset was selected and loaded in a MongoDB database. That dataset collects all the nobel prizes that were given in the last century. These are the general use steps to take into account:

- Swagger access: http://localhost:8081/swagger-ui/index.html
- MongoDB connection String: mongodb://localhost
- Database name: nobelprize
- Collection name: prizes
- Maven execution command (same directory as pom.xml): mvn spring-boot:run

## Dataset and MongoDB
The dataset has a particular structure, each prize is a JSONObject but at the same time this prizes have three main attributes; two Strings and an JSONObject Array. The main idea was to deal with a certain level of depth in our datasets avoiding picking plain datasets. The dataset selected for the project was picked from a collection of datasets located in GitHub. 

This is the link: https://github.com/jdorfman/awesome-json-datasets?tab=readme-ov-file

As mentioned before the dataset was originally taken as a JSON document. Before the dataset was imported to a MongoDB database it suffered a small change on the structure. The Nobel Prizes were inside an array, having this structure and importing to MongoDB we got as a result a single document inside our collection. Inside that array they were 670 prizes but importing in this way makes no sense, so the array was deleted and in that way Mongo could import each prize as a single document. Before starting making the import is compulsory to create a new connnection to our MongoDB server. In this case we configured a connection to the localhost using this connection string: mongodb://localhost.

## Rest Service

The Rest service has a particular structure. Using this service the user can make different types of request, those types are the same that a CRUD app makes; create(POST), read(GET), update(PUT) and delete(DELETE). Each endpoint manages a different type of request, thats why depending which one is going to be executed the service will do one type of operation or another. The request can be made using the Swagger user interface or another development environment as Imsomnia or Postman. From Swagger you can access the different endpoints, see the model schemas and get the api doc in yaml format. This is an overview:



|Type|Endpoint|Result|
|----|--------|------|
|GET |/nobel/sariguztiak| This returns all the Prizes in JSON format|
|GET |/nobel/sariabilatu| This returns the Prize that matches the passed parameter|
|GET |/nobel/bilatuKategoriaUrteka|This returns the Prize that matches the passed parameters|
|POST |/nobel/sariberria|"Nobel Saria era egokian gehitu da"|
|PUT |/nobel/sariaeditatu/{_id}|Response headers and response (ResponseEntity HttpRequestCode):<br><br>connection: keep-alive<br>content-length: 0<br>date: Wed,10 Jan 2024 15:20:58 GMT<br>keep-alive: timeout=60<br>CODE: 200<br>Description: OK|
|DELETE |/nobel/sariaezabatuid/{_id}|RESPONSE OF THE HTTPREQUEST STATUS CODE:<br><br>connection: keep-alive<br>content-length: 0<br>date: Wed,10 Jan 2024 15:17:58 GMT<br>keep-alive: timeout=60<br>CODE: 200<br>Description: OK<br><br>FROM TERMINAL:<br><br>Successful case: "Nobel Saria ezabatua izan da, ezabatutako kopurua: 1"<br>Failure case: "Errore bat gertatu da Nobel Saria ezabatzen, saiatu berriro mesedez..."|
|DELETE |/nobel/sariaezabatu|Successful case: "Nobel Saria era egokian ezabatu da"<br>Failure case: "Errore bat gertatu da Nobel Saria ezabatzen, saiatu berriro mesedez..."|

## MongoDB


## Java Project
