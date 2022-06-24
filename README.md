# nfldepthchart

This is a project which implements all the functions described in the NFL Depth Charts documents.Include 4 main functions: addPlayerToDepthChart, removePlayerFromDepthChart, getBackups and getFullDepthChart.

This project will expose the functions as rest services.

1. Environment requirements:

   (1)java(version>=11)

   (2)gradle(version>=7.4.2)

   (3)docker(version>=4.8.2)

   (4)docker-compose(version>=1.29.2)

   3 and 4 (docker and docker-compose) is not a must if you only run locally.


2. How to run:

   (1)Run locally:

   <1> clone this repository to somewhere in your local laptop.

       git clone git@github.com:wfdong/nfldepthchart.git

   <2> cd into project folder, foler 'nfldepthchart', and run command:
       
       ./gradlew bootRun

   <3> after step 2, the service will be running and you can access by this 4 urls:

       http://localhost:8080/addplayer

       http://localhost:8080/removeplayer

       http://localhost:8080/getbackups

       http://localhost:8080/getFullDepthChart

       (parameter details listed in end of the document)


    (2) Run in docker:

    <1> clone this repository to somewhere in your local laptop.

        git clone git@github.com:wfdong/nfldepthchart.git

    <2> cd into project folder, foler 'nfldepthchart', and run command:

        docker compose up

        Note: once this command success, there will be 4 services start and running: 1 nginx service, 3 nfldepthchart rest services(they will provide service in polling mode). 

    (3) Run tests and build a jar file:

        Run test: ./gradlew test

        build: ./gradlew build

        clean: ./gradlew clean

    note: the requst collections attached in this project as well:  NFLDepthChart_local.postman_collection.json, NFLDepthChart-Docker.postman_collection.json   If you have a postman installed, you will be able to run it by just importing these collection files.


Access the service:

From local using port 8080.

If run in docker just need to change port to 8087.

<1> addPlayerToDepthChart

http://localhost:8080/addplayer

Http Method: POST

Http Header:

{
    content-type: application/json
}

Body:
{
    "position": "QB",
    "player": {
        "number":"12",
        "name": "Tom Brady"
    },
    "positionDepth":"0"
}

<2> removePlayerFromDepthChart

http://localhost:8080/removeplayer

Http Method: POST

Http Header:

{
    content-type: application/json
}

Body:
{
    "position": "QB",
    "player": {
        "number":"12",
        "name": "Tom Brady"
    }
}

<3> getBackups

http://localhost:8080/getbackups

Http Method: POST

Http Header:

{
    content-type: application/json
}

Body:
{
    "position": "QB",
    "player": {
        "number":"12",
        "name": "Tom Brady"
    }
}

<4> getFullDepthChart

http://localhost:8080/getFullDepthChart

Http Method: GET

Http Header:

{
    content-type: application/json
}

few snapshots:
![image](https://user-images.githubusercontent.com/7520014/175550311-21ddf541-abeb-43f1-9bdf-d406b5b93dc7.png)
![image](https://user-images.githubusercontent.com/7520014/175550418-81cde2c8-2632-49bd-a1db-61e0d9b902a7.png)
![image](https://user-images.githubusercontent.com/7520014/175550485-0e157ca1-6612-4a5b-aacc-fd33a2d57fe0.png)
![image](https://user-images.githubusercontent.com/7520014/175550698-3ea48283-6207-414e-9b50-a5eeb85aac6a.png)

docker containerization:
<img width="1088" alt="image" src="https://user-images.githubusercontent.com/7520014/175551162-bb077d50-f643-4633-b167-757780cdebc7.png">
![image](https://user-images.githubusercontent.com/7520014/175551320-da6da6fe-32d3-4b6c-9a63-46f7be7e1ab3.png)






}
