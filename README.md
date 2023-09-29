# CinemaApp Documentation

## Table of Contents
1. [Overview](#overview)
2. [User Controller](#user-controller)
   - [Get User Profile](#1-get-user-profile)
   - [Get User Tickets](#2-get-user-tickets)
   - [Reserve Ticket](#3-reserve-ticket)
   - [Cancel Ticket Reservation](#4-cancel-ticket-reservation)
   - [Get Ticket Price](#5-get-ticket-price)
3. [Movie Controller](#movie-controller)
   - [Add Movie](#1-add-movie)
   - [Get Movie by ID](#2-get-movie-by-id)
   - [Get All Movies](#3-get-all-movies)
   - [Delete Movie by ID](#4-delete-movie-by-id)
   - [Set Movie Showtime](#5-set-movie-showtime)
4. [Reporting Controller](#reporting-controller)
   - [Get Movie Statistics](#1-get-movie-statistics)
   - [Get Daily Sales Report](#2-get-daily-sales-report)
5. [Theater Controller](#theater-controller)
   - [Add Theater](#1-add-theater)
   - [Get Theater by ID](#2-get-theater-by-id)
   - [Get All Theaters](#3-get-all-theaters)
   - [Delete Theater by ID](#4-delete-theater-by-id)
   - [Set Theater Details](#5-set-theater-details)
   - [Get Theater Reservations Board](#6-get-theater-reservations-board)
6. [Tickets Controller](#tickets-controller)
   - [Get Theater Tickets](#1-get-theater-tickets)
   - [Initialize Tickets](#2-initialize-tickets)
   - [Delete Tickets](#3-delete-tickets)
   - [Admin Reserve Ticket](#4-admin-reserve-ticket)
   - [Cancel Ticket Reservation](#5-cancel-ticket-reservation)
   - [Get Ticket Price](#6-get-ticket-price)
7. [Security](#security)
8. [Getting Started](#getting-started)
9. [Contributions](#contributions)

## Overview
CinemaApp is a Spring Boot application that manages cinema-related operations, including user authentication, movie and theater management, ticket reservations, and reporting. This document provides an overview of the available endpoints and their functionalities.

## User Controller
**Base URL:** `/cinema/user`

### 1. Get User Profile
   - **URL:** `/profile`
   - **Method:** `GET`
   - **Description:** Retrieve the profile information of the authenticated user.
   - **Access Control:** Accessible by both users and admins.
   - **Response Format:** JSON
   - **Response Body:**
     - `AppUserDto`: User profile information including username, email, and other details.

### 2. Get User Tickets
   - **URL:** `/tickets`
   - **Method:** `GET`
   - **Description:** Retrieve a list of tickets associated with the authenticated user.
   - **Access Control:** Accessible by both users and admins.
   - **Response Format:** JSON
   - **Response Body:**
     - List of `TicketDto`: User's ticket information including movie, theater, seat, and other details.

### 3. Reserve Ticket
   - **URL:** `/tickets/reservation`
   - **Method:** `PATCH`
   - **Description:** Reserve a ticket for a specific theater, row, and column.
   - **Access Control:** Accessible by both users and admins.
   - **Request Body:**
     - `theaterId` (Long): Identifier of the theater for which the ticket is reserved.
     - `row` (int): Row number for the reserved seat.
     - `col` (int): Column number for the reserved seat.
   - **Response Format:** JSON
   - **Response Body:**
     - `TicketDto`: Reserved ticket information including movie, theater, seat, and other details.

### 4. Cancel Ticket Reservation
   - **URL:** `/tickets/reservation`
   - **Method:** `DELETE`
   - **Description:** Cancel the reservation for a specific ticket.
   - **Access Control:** Accessible by both users and admins.
   - **Request Parameter:**
     - `ticketId` (Long): Identifier of the ticket to be canceled.

### 5. Get Ticket Price
   - **URL:** `/tickets/price`
   - **Method:** `GET`
   - **Description:** Retrieve the price of a ticket for a specific theater and seat.
   - **Access Control:** Accessible by both users and admins.
   - **Request Parameters:**
     - `theaterId` (Long): Identifier of the theater.
     - `row` (int): Row number for the seat.
     - `col` (int): Column number for the seat.
   - **Response Format:** JSON
   - **Response Body:**
     - `TicketSimpleDto`: Ticket price information for the specified seat.

## Movie Controller
**Base URL:** `/cinema/movies`

### 1. Add Movie
   - **URL:** `/`
   - **Method:** `POST`
   - **Description:** Add a new movie to the system.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Response Format:** JSON
   - **Response Body:**
     - `MovieDto`: Details of the added movie, including title, description, and other attributes.

### 2. Get Movie by ID
   - **URL:** `/{id}`
   - **Method:** `GET`
   - **Description:** Retrieve details of a movie by its unique identifier.
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the movie.
   - **Response Format:** JSON
   - **Response Body:**
     - `MovieDto`: Details of the retrieved movie, including title, description, and other attributes.

### 3. Get All Movies
   - **URL:** `/`
   - **Method:** `GET`
   - **Description:** Retrieve a list of all movies available in the system.
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Response Format:** JSON
   - **Response Body:**
     - List of `MovieDto`: Details of all movies, including title, description, and other attributes.

### 4. Delete Movie by ID
   - **URL:** `/{id}`
   - **Method:** `DELETE`
   - **Description:** Delete a movie from the system by its unique identifier.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the movie to be deleted.

### 5. Set Movie Showtime
   - **URL:** `/{id}`
   - **Method:** `PUT`
   - **Description:** Set the showtime for a movie by its unique identifier.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the movie for which the showtime is set.
     - `showTime` (LocalDateTime): New showtime for the movie.
   - **Response Format:** JSON
   - **Response Body:**
     - `MovieDto`: Updated details of the movie, including the new showtime.

## Reporting Controller
**Base URL:** `/cinema/movies/{MovieId}`

### 1. Get Movie Statistics
   - **URL:** `/statistics`
   - **Method:** `GET`
   - **Description:** Retrieve statistics for a specific movie.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `MovieId` (Long): Identifier of the movie for which statistics are requested.
   - **Response Format:** JSON
   - **Response Body:**
     - `MovieStatisticsDto`: Statistical data for the movie, including details like total sales, ratings, and other statistics.

### 2. Get Daily Sales Report
   - **URL:** `/daily-sales`
   - **Method:** `GET`
   - **Description:** Retrieve a daily sales report for a specific movie.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `MovieId` (Long): Identifier of the movie for which the daily sales report is requested.
   - **Response Format:** JSON
   - **Response Body:**
     - `DailySalesReportDto`: Daily sales report data for the movie, including date-wise sales figures and related information.

## Theater Controller
**Base URL:** `/cinema/theaters`

### 1. Add Theater
   - **URL:** `/`
   - **Method:** `POST`
   - **Description:** Add a new theater to the system.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Response Format:** JSON
   - **Response Body:**
     - `TheaterDto`: Details of the added theater, including name, capacity, and other attributes.

### 2. Get Theater by ID
   - **URL:** `/{id}`
   - **Method:** `GET`
   - **Description:** Retrieve details of a theater by its unique identifier.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the theater.
   - **Response Format:** JSON
   - **Response Body:**
     - `TheaterDto`: Details of the retrieved theater, including name, capacity, and other attributes.

### 3. Get All Theaters
   - **URL:** `/`
   - **Method:** `GET`
   - **Description:** Retrieve a list of all theaters available in the system.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Response Format:** JSON
   - **Response Body:**
     - List of `TheaterDto`: Details of all theaters, including name, capacity, and other attributes.

### 4. Delete Theater by ID
   - **URL:** `/{id}`
   - **Method:** `DELETE`
   - **Description:** Delete a theater from the system by its unique identifier.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the theater to be deleted.

### 5. Set Theater Details
   - **URL:** `/{id}`
   - **Method:** `PUT`
   - **Description:** Update details of a theater by its unique identifier.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the theater for which details are updated.
     - `movieName` (String): New name of the movie associated with the theater.
     - `rows` (int): New number of rows in the theater.
     - `columns` (int): New number of columns in the theater.
   - **Response Format:** JSON
   - **Response Body:**
     - `TheaterDto`: Updated details of the theater, including name, capacity, and other attributes.

### 6. Get Theater Reservations Board
   - **URL:** `/{id}/reservations-board`
   - **Method:** `GET`
   - **Description:** Retrieve a reservations board for a specific theater, indicating the availability status of seats (A for available, R for reserved).
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameter:**
     - `id` (Long): Identifier of the theater for which the reservations board is requested.
   - **Response Format:** JSON
   - **Response Body:**
     - `TheaterBoardDto`: Reservations board data for the theater, indicating seat availability.

## Tickets Controller
**Base URL:** `/cinema/theaters/{theaterId}/tickets`

### 1. Get Theater Tickets
   - **URL:** `/`
   - **Method:** `GET`
   - **Description:** Retrieve a list of tickets for a specific theater.
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameter:**
     - `theaterId` (Long): Identifier of the theater for which tickets are requested.
   - **Response Format:** JSON
   - **Response Body:**
     - List of `TicketSimpleDto`: Ticket information including seat details and status.

### 2. Initialize Tickets
   - **URL:** `/`
   - **Method:** `PUT`
   - **Description:** Initialize tickets for a specific theater, typically used during theater setup.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `theaterId` (Long): Identifier of the theater for which tickets are initialized.

### 3. Delete Tickets
   - **URL:** `/`
   - **Method:** `DELETE`
   - **Description:** Delete all tickets for a specific theater.
   - **Access Control:** Accessible only by administrators (`ROLE_ADMIN`).
   - **Request Parameter:**
     - `theaterId` (Long): Identifier of the theater for which tickets are deleted.

### 4. Admin Reserve Ticket
   - **URL:** `/reservation`
   - **Method:** `PATCH`
   - **Description:** Reserve a ticket for a specific theater, row, column, and user (admin reservation).
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameters:**
     - `theaterId` (Long): Identifier of the theater for which the ticket is reserved.
     - `row` (int): Row number for the reserved seat.
     - `col` (int): Column number for the reserved seat.
     - `userId` (Long): Identifier of the user for whom the ticket is reserved.
   - **Response Format:** JSON
   - **Response Body:**
     - `TicketDto`: Reserved ticket information including movie, theater, seat, and other details.

### 5. Cancel Ticket Reservation
   - **URL:** `/reservation`
   - **Method:** `DELETE`
   - **Description:** Cancel the reservation for a specific theater, row, and column (admin cancellation).
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameters:**
     - `theaterId` (Long): Identifier of the theater for which the reservation is canceled.
     - `row` (int): Row number of the reserved seat.
     - `col` (int): Column number of the reserved seat.

### 6. Get Ticket Price
   - **URL:** `/price`
   - **Method:** `GET`
   - **Description:** Retrieve the price of a ticket for a specific theater and seat.
   - **Access Control:** Accessible by both users and administrators (`ROLE_USER`, `ROLE_ADMIN`).
   - **Request Parameters:**
     - `theaterId` (Long): Identifier of the theater.
     - `row` (int): Row number for the seat.
     - `col` (int): Column number for the seat.
   - **Response Format:** JSON
   - **Response Body:**
     - `TicketSimpleDto`: Ticket price information for the specified seat.

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

