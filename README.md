To create artifact run ./gradlew build.
Artifact named west-stein-rest-service-0.1.0.jar will be placed to build/libs folder
to run artifact (make sure that java 8 is installed) from command line: java -jar build/libs/west-stein-rest-service-0.1.0.jar
to run artifact from gradle: ./gradlew bootRun
Project is using H2 in memory for database, so no DB need to be setup.
rest service will be available at localhost:8080
when you open it, you will be redirected to swagger-ui.html custom rest documentation page.
It will provide two resources. POST application/apply to create application, and GET application to see all save applications.
There is test file with valid JSON for application creation.

