# person-service

Steps to run

  **1.Clone this Repository **
    `git clone https://github.com/nageshnkt2007/person-service.git`
    
    
  **2. Go to project directory**
    `cd person-service`
  **3. Build this Project using below command**
    `mvnw clean install`
    
    
  **4. To ignore test cases while build use command**
    `mvnw clean install -Dmaven.test.skip=true`
    
    
  **5. Run Docker build to build a docker image of this project**
    `docker build -f Dockerfile -t person-service.jar .`
    
    
  **6. Use docker run to run this project on 8080 port (make sure no other app is running on the same port)**
    `docker run -p 8080:8080 person-service.jar`
    
    
  **7. person-service will be running on 8080 port**
    Swagger documentation:   `http://localhost:8080/swagger-ui.html#/`
    
    
  **8. To quickly setup API testing Please import below postman collection.**
    `/postman/person-service.postman_collection.json`
