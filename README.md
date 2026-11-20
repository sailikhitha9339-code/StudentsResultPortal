# StudentResultPortal

## Project Description
The Student Result Management System is a web-based application developed using JSP, Servlet, JDBC, and MySQL. It helps manage student information, subject details, and examination marks. It also calculates student results and provides a facility to search results using Student ID.

## Technologies Used
- Java
- JSP (JavaServer Pages)
- Jakarta Servlet
- JDBC
- MySQL Database
- HTML
- CSS
- Apache Tomcat
- NetBeans IDE

## Features

### 1. Student Management
- Add new student details
- View student records
- Update student information
- Delete student records

### 2. Subject Management
- Add new subjects
- View subject details
- Update subject information
- Delete subjects

### 3. Marks Management
- Add student marks
- View marks
- Update marks
- Delete marks

### 4. Result Management
- Calculate total marks
- Calculate percentage
- Display Pass/Fail status
- View student results

### 5. Search Facility
- Search student results using Student ID
- Display the corresponding result details

## Database Tables
The project uses a MySQL database named `studentresultportal`.

Main tables:
- `students`
- `subjects`
- `marks`

## Software Requirements
- JDK
- NetBeans IDE
- Apache Tomcat Server
- MySQL Server
- MySQL JDBC Driver

## Installation and Setup

1. Clone or download this repository.
2. Open the project in NetBeans IDE.
3. Create a MySQL database named `studentresultportal`.
4. Import or execute the project database SQL file, if provided.
5. Configure the database connection in `DBConnection.java`.
6. Add the MySQL JDBC Driver to the project if required.
7. Configure Apache Tomcat in NetBeans.
8. Clean and Build the project.
9. Run the project on the Tomcat server.

## Project Structure

- `model` – Contains Student and Subject model classes.
- `dao` – Contains database operations.
- `service` – Contains application service methods.
- `controller` – Contains Servlets for handling requests.
- `util` – Contains database connection utilities.
- `Web Pages` – Contains JSP pages and user interface files.

## Objective
The main objective of this project is to simplify student record management and result processing through a web-based application using Java technologies and a MySQL database.

## Project Team

Developed By:
K. Venkata Sai Likhitha

Team Members:

D. Pavan Sai Manikanta

A. Rama Shiva

Project Name: StudentResultPortal
---

