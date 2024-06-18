# User API

This is a Spring Boot application that provides a RESTful API for managing user information.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Features

- Register a new user
- Find All users
- Find users by username

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- A SQL database (e.g., MySQL, PostgreSQL)

## Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/user-api.git
    cd user-api
    ```

2. **Configure the database**:
   - Update the `application.properties` file in the `src/main/resources` directory with your database configuration:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
     spring.datasource.username=yourusername
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Install dependencies**:
    ```sh
    mvn clean install
    ```

## Running the Application

1. **Start the application**:
    ```sh
    mvn spring-boot:run
    ```

2. The application will be available at `http://localhost:8080`.

## API Endpoints

### Register a New User

- **URL**: `/api/user/register`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "johndoe",
    "email": "john.doe@example.com"
  }
  ### Register a New User

- **URL**: `/api/user/{user}`
- **example**:`/api/user/john`
- **Method**: `GET`
- **ID AUTO GENERATED**
- **Request Body**:
  ```json
  #Response
  {
    "id": 1,
    "username": "johndoe",
    "email": "john.doe@example.com"
  }
  Technologies Used
Spring Boot
Spring Data JPA
Hibernate
MySQL (or another SQL database)
Maven


### Explanation

1. **Features**: Highlights the main functionalities of your API.
2. **Prerequisites**: Lists the required software and tools.
3. **Installation**: Provides step-by-step instructions to set up the application.
4. **Running the Application**: Describes how to start the application.
5. **API Endpoints**: Details the available endpoints, methods, request bodies, and responses.
6. **Technologies Used**: Lists the technologies and tools used in the project.
7. **Contributing**: Encourages contributions and explains how to submit changes.
8. **License**: Specifies the project's licensing information.

This structure ensures that anyone cloning your repository can easily understand how to set up, run, and use your application.
This project is licensed under the MIT License - see the LICENSE file for details.
