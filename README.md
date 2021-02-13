# person-service

## Steps to run.


  ### 1. Clone this Repository
  
  
    `git clone https://github.com/nageshnkt2007/person-service.git`
    
    
  ### 2. Go to project directory
    `cd person-service`
  ### 3.Build this Project using below command
    `mvnw clean install`
    
    
  ### 4. To ignore test cases while build use command
    `mvnw clean install -Dmaven.test.skip=true`
    
    
  ### 5. Run Docker build to build a docker image of this project
    `docker build -f Dockerfile -t person-service.jar .`
    
    
  ### 6. Use docker run to run this project on 8080 port (make sure no other app is running on the same port)
    `docker run -p 8080:8080 person-service.jar`
    
    
  ### 7. person-service will be running on 8080 port
    Swagger documentation:   `http://localhost:8080/swagger-ui.html#/`
    Below is the list of Operations in person-service
    `person-resource :
      POST /person/
      createPerson

      PUT /person/
      updatePerson

      GET /person/all
      getAllPersons

      DELETE /person/batch/
      deletePersons

      POST /person/batch/
      createPersons

      PUT /person/batch/
      updatePersons

      GET /person/{id}
      getPerson`
    
    
  ### 8. To quickly setup API testing Please import below postman collection.
     For checking how to install/import a postman collection please visit `https://developer.ft.com/portal/docs-start-install-postman-and-import-request-collection`
    `/postman/person-service.postman_collection.json`
  ### 9. Constraints/Assumptions
    - `/getall` API accepts an optional parameter `page` to support pagination of Person list, This Pagination count in each is configurable in application-dev.yml for dev profile for testing. Currently it is set as `5` for dev profile.
    - In Memory Database is used for this POC
    - Test cases in this project were written considering In Memory Database and in real production scenario Mocking framework like Mockito/Power-Mockito would be suggested.
    - In order to Validate the data for `Person Entity`, We are eagerly checking with all the parameters being mandatory.
    - If the Data is not Validated APIs would return a response code `412 Precondition Failed`
    - For enhancing performance of saving the list of `Person` Entities have enabled Batch processing with current batch size bieng 2 for dev profile.(This is only for testing and could be increased as per expected load on the application in application.yml-batch_size file for that profile)
    - generate_statistics: true is also enabled for dev profile to verify the DB commits in batches.
    - Added CRUD operations for Single Person entity as well, the APIs are working but have not written test cases for their Service calls.(Which might be visible if you check code coverage)
