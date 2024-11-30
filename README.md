# Reservation Management System for Nail Shops


## 1. Problem Statement
**Reservation System:** Enable customers to book appointments online, reducing wait times and improving scheduling efficiency.

**Service Selection:** Provide customers with a range of services to choose from during booking. This includes, but is not limited to:

 - Manicure
 - Pedicure
 - Acrylic Nails
 - Gel & Shellac Nails
 - Nail Art
 - Dip Powder Nails
 - Polish Change
 - Hand and Foot Treatments
 - Nail Repair
   
**Billing and Receipts:** Generate billing and receipts based on chosen services.

**Loyalty Points Tracking:** Implement a loyalty program where customers can earn points for each reservation, which can be tracked and redeemed for future services.

## 2. Requirement Analysis
## 2.1 Functional Requirement
**Manager**
 - Create/Delete technician
 - Create/Delete manager
 - Create/Update/Delete service
 - View/Update/Cancel/Complete appointment
 - Login
 - Update profile
 - Change password

**Technician**
 - Login
 - Update profile
 - Change password
 - View appointments

**Customer**
- Login
 - Update profile
 - Change password
 - View appointments
 - Book/Update/Cancel appointments
   
## 2.2 Non-functional Requirement
**Not-required authentication**
 - Create customer
 - Login
   
**Required authentication**
 All functionalities except login and create customer must be authenticated and authorizated by role.

**Password must be encrypted**

**Delete functionality: do not physically delete**

## 3. UML Diagram
![image](https://github.com/user-attachments/assets/939da771-95ff-4975-a140-c1ffff519056)

![image](https://github.com/user-attachments/assets/d2c09c64-25c6-442d-b1f5-a137382f9e39)

![image](https://github.com/user-attachments/assets/5949d2d4-feb8-47e9-8a9d-526c58022622)

## 4. Architecture
**Technologies used:**

**Back-end:** 
  - SpringBoot, Spring MVC, Spring Data JPA, Spring Security, MapStruct
  - Database: MySQL
  - Authentication and Authorization: JWT token
  - Testing: JUnit, Mockito, Postman

**Front-end**
  - Angular 17.3
  - Bootstrap, jwt-decode

![image](https://github.com/user-attachments/assets/bb80ca5e-f048-4fc5-89ec-738b7785396e)

## 5. ERD
![image](https://github.com/user-attachments/assets/4a98ff38-a49b-41f0-ab4f-2c13e2a77aa1)

## 6. Setup Instructions
## 6.1 Back-end
**Using Docker compose to deploy back-end APIs and MySQL on Azure App Services**

  -	Create Dockerfile for back-end to build image
  -	Create Docker compose file including MySQL and back-end API
  -	Create jar file (make sure to comment out database information in application.properties file. If not, it does not work when deploying to Azure)
    
    mvn clean package
  -	Build docker image for back-end API
    
    docker build -t butbui86/nail-shop-api:1.0.1 .
  -	Push back-end image to Docker Hub (https://hub.docker.com/)
    
    docker push butbui86/nail-shop-api:1.0.1
  -	Create Web App
    
    Go to App Services, create Web App and select Publish as Container.

   ![image](https://github.com/user-attachments/assets/57807b9a-74b0-44c2-84b2-3821094c433a)

   In Deployment -> Deployment Center, configure
    
    Container type: Docker Compose
    
	   Registry source: Docker Hub
    
	   And upload the Docker compose file prepared into Config textbox
    
   ![image](https://github.com/user-attachments/assets/6edf87d8-c4a6-4840-ba39-3c2d6be0cb23)



## 6.2 Front-end
