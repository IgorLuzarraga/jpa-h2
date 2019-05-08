Spring Boot Book´s Repository With Vaadin
==============

Spring Boot application that implements a book's repository using 
CRUD (Create, Read, Update, Delete) operations to create and recover
objects (books) stored in a H2 data base using Spring Data JPA.
The GUI is made using the framework Vaadin.

Modules:
========
- Spring Boot
- Vaadin - Java web framework - https://vaadin.com
- Spring Data JPA
- H2 In-Memory Database

Build the jar:
-------------------------
./gradlew build

Run the jar:
-------------------------
java -jar build/libs/jpa-h2-0.0.1-SNAPSHOT.jar

Test the application:
-------------------------
Connect to the server via http://localhost:8080 and use the gui
to check the CRUD operations.

