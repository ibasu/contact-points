<span><img src="https://www.peakinsight.com.au/wp-content/uploads/2020/08/Telstra-Logo.png" height="60"></span>
<span><img src="https://www.comparetv.com.au/wp-content/uploads/2018/11/belong-logo-1.png" height="60"></span>

## Prerequisites
- JDK 8+ installed with JAVA_HOME configures
- Gradle configured

## Supported technologies

Languages:

* Java
* Groovy

Framework:
* Spring Boot

## Getting started

### Problem Description
Provide interface specifications for the above functions/capabilities.
Provide an implementation of the formulated specifications.
You can assume the phone numbers as a static data structure that is initialised when your program runs.

### Solution

### Project Name : contact-points

##### This is a simple gradle java spring boot application which which exposes 3 REST API endpoints
The contact-points microservice is part of customer master umbrella. The contact points ms, is responsible for 
maintaining customer's contact points like phone numbers which allows end users to read and write using REST API.
Following are the 3 endpoints exposed by this microservice:
- GET /cm/v1/contactpoints/ : Fetches all contact points for all customers
- GET /cm/v1/contactpoints/customer/{customerId} : Fetches all contact points for the given customer
- PUT /cm/v1/state/ : Change the state of the contact point like Active, Inactive etc.
                    Supported states are(case sensitive):
                    1. NEW
                    2. ACTIVE
                    3. INACTIVE
                    4. BLOCK
                    5. SURRENDER
                    6. HOLD

##### Design Considerations
- A java based spring boot REST application which accepts HTTP requests in JSON format ONLY
- The program returns the custom result object which contains the output and errors(if any)
- The application uses in memory H2 database as the data store
- The initial data load or lov is located in **resources/db/migration/V1.1__log.sql**
- User of this application can add or remove data in the above mentioned lov sql file. 

```
### Build the spring boot application
```
./gradlew 
```

### Start the gradle Java application
#####Or
```groovy
./gradlew bootRun
```
#####Or
```bash
java -jar build\libs\contact-points-0.0.1-SNAPSHOT.war
```

### Swagger
Swagger can be found in the below url:

```
http://localhost:8080/cm/swagger-ui.html
```


##### Get All Contact Points / Phone Numbers
```bash
curl --location --request GET 'http://localhost:8080/cm/v1/contactpoints/'
' 
```
- Response
```json
[
    {
        "customerId": "CM10000001",
        "contactNoCode": "04",
        "contactNo": 12345678,
        "status": "NEW",
        "creationTime": "2021-04-18T19:15:52.027+10:00"
    }
]
```

##### Get All Contact Points for a given customer / Phone Numbers
```bash
curl --location --request GET 'http://localhost:8080/cm/v1/contactpoints/customer/CM10000001'
' 
```
- Response
```json
[
    {
        "customerId": "CM10000001",
        "contactNoCode": "04",
        "contactNo": 12345678,
        "status": "NEW",
        "creationTime": "2021-04-18T19:15:52.027+10:00"
    }
]
```

##### Activate Phone Number
- Example 1: 
```bash
curl --location --request PUT 'http://localhost:8080/cm/v1/contactpoints/state' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerId": "CM10000001",
    "contactNoCode": "04",
    "contactNo": 12345678,
    "status": "ACTIVE",
    "userId": "e10001",
    "userNotes": "Activating contactNo"
}
'
```
- Response: 
```json
{
    "customerId": "CM10000001",
    "contactNoCode": "04",
    "contactNo": 12345678,
    "status": "ACTIVE",
    "creationTime": "2021-04-18T21:20:15.816+10:00",
    "userId": "e10001",
    "userNotes": "Activating contactNo"
}
```

##### Invalid Request to Activate Phone Number
- Example 1: 
```bash
curl --location --request PUT 'http://localhost:8080/cm/v1/contactpoints/state' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerId": "CM10000001",
    "contactNoCode": "04",
    "contactNo": 123456978,
    "status": "ACTIVE",
    "userId": "e10001",
    "userNotes": "Activating contactNo"
}
'
```
- Response: 
```json
[
    {
        "errorId": "400 BAD_REQUEST",
        "errorMessage": "numeric value out of bounds (<8 digits>.<0 digits> expected)",
        "timestamp": "18-04-2021 09:25:16",
        "fieldName": "contactNo",
        "fieldPath": null
    }
]
```