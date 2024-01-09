# NobelPrizesMongoRestApi by Julen Herrero

This project has been developed for the Data Access subject of the second course of Development of Multiplatform Applications in Uni Eibar. The main task is to develop a Rest Api that access a MongoDB database and to learn how a Rest Api can fit in a web application. In this case a JSON dataset was selected and loaded in a MongoDB database. That dataset collects all the nobel prizes that were given in the last century. 

## Dataset and MongoDB
The dataset has a particular structure, each prize is a JSONObject but at the same time this prizes have three main attributes; two Strings and an JSONObject Array. The main idea was to deal with a certain level of depth in our datasets avoiding picking plain datasets. The dataset selected for the project was picked from a collection of datasets located in GitHub. 

This is the link: https://github.com/jdorfman/awesome-json-datasets?tab=readme-ov-file

As mentioned before the dataset was originally taken as a JSON document. Before the dataset was imported to a MongoDB database it suffered a small change on the structure. The Nobel Prizes were inside an array, having this structure and importing to MongoDB we got as a result a single document inside our collection. Inside that array they were 670 prizes but importing in this way makes no sense, so the array was deleted and in that way Mongo could import each prize as a single document. Before starting making the import is compulsory to create a new connnection to our MongoDB server. In this case we configured a connection to the localhost using this connection string: mongodb:/localhost.

## Rest Service

|Type|Endpoint|Result|
|----|--------|------|
|GET |        |      |
