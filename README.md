# Patika.dev Mobile Action - Graduation Project

## About
Reading air pollution information by city name and date range, using a RESTful API.


## Sources

- Historical Air Pollution API - https://openweathermap.org/api/air-pollution#history
- Geocoding API - https://openweathermap.org/api/geocoding-api
- Wiki Page on CAQI - https://en.wikipedia.org/wiki/Air_quality_index#CAQI
- AQI Breakpoints - https://aqs.epa.gov/aqsweb/documents/codetables/aqi_breakpoints.html

## Requirements & Effort
- [x] readme file
- [x] Clean Code & SOLID 
- [x] Microservice & Distributed Systems
- [ ] Unit Testing
- [x] Design Patterns
- [x] Documentation (SwaggerUI)
- [x] Use of Hibernate (JPA) 
- [x] Logging Mechanism
 
## Swagger-UI Documentation

### Controller API
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/final.JPG" width="700">

### 1. Attempting to query an invalid date range (before 26.10.20)
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/1.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/2.JPG" width="700">

### 2. Attempting to query an invalid city.
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/3.JPG" width="700">

### 3. Querying a city, without specifying a date range.

<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/4.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/5.JPG" width="700">

### 3. Querying a city, with a date range.
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/6.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/7.JPG" width="700">

#### Logging output 
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/8.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/9_examplelog.JPG" width="700">

### 4. Same query called
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/6.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/10_calledagain.JPG" width="700">

### DB results
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/13_db.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/14_db.JPG" width="700">

### 5. Attempt to delete all records by city name that does not exist in the database
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/15.JPG" width="700">

### 6. Delete all records by city name
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/16.JPG" width="700">

### 7. DB results after deletion
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/17.JPG" width="700">

### Delete a single record by city name and date
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/18.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/19.JPG" width="700">

### Attempt to delete a range of dates by city name 
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/20.JPG" width="700">
<img src="https://github.com/ceydas/microservices_final_project/blob/main/swagger-ui_captures/21.JPG" width="700">



