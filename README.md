# CinemaApp Documentation
### CinemaApp is a Spring Boot application that manages cinema-related operations, including user authentication, movie and theater management, ticket reservations, and reporting. This documentation provides an overview of the available endpoints and their functionalities.

**Documentation(Api Collection) Link** : https://documenter.getpostman.com/view/29519819/2s9YJgTLMa

## Security
Authentication and authorization in the CinemaApp are implemented using JSON Web Tokens (JWT). Access to certain endpoints is restricted based on user roles (`ROLE_USER`, `ROLE_ADMIN`). 

## Getting Started
1. Clone the repository: `git clone https://github.com/your-username/CinemaApp.git`
2. Build and run the application: `./mvnw spring-boot:run`
3. Access the application at `http://localhost:8080`
4. Create users and movies as needed using the aprropriate endpoints.
5. Create a Theater.
6. Set the theater ( Movie , rowNumber , colNumber ).
7. Initialize tickets for your theater.
8. Now you can start reserving the tickets of this theater. 

## Contributions
Contributions are welcome! If you'd like to contribute to this project, please fork the repository and create a pull request with your changes.

