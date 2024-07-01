<h1 align="center"> Welcome to Literalura </h1>
<h3 align="center"> Console application to preserve information about books and authors </h3>

![Badge in Development](https://img.shields.io/badge/Version-1.0-green) ![Last Update](https://img.shields.io/badge/Last%20update-30%2F06%2F2024-blue)

# Index

1. [Project Description](#project-description)
2. [Tools Used](#tools-used)
3. [Future Plans](#future-plans)

## Project Description

This Java application (with Spring implementation) was created as one of the final projects of the "Java, Spring and Databases" module of the "Back-End Programming with Java" course from Alura ONE (a course provided as a product of the alliance between Alura and Oracle ONE). The function of this small console program is to search for data of a specific book on the web, using the Gutendex API (https://gutendex.com/), and save data of said book, as well as its author, in a database (Said database is not provided in this repository for security reasons), thus allowing access to this data in a practical and fast way in the future.

### Features  

* Ability to search for a book requested by the user, as well as its author, and save their information in a durable way in a database.
* Ability to access the complete record of books and authors saved in the database.
* Ability to retrieve a record of books or authors with applied filters (book language, year the author was alive, etc.).

## Tools Used

Literalura was developed using the following technologies:

* Java (the base language in which the application was written)
* Spring (Java framework that provides infrastructure support for developing robust and maintainable enterprise applications)
* PostgreSQL (database management system used)
* pgAdmin 4 (tool for managing and reviewing the PostgreSQL database)

## Future Plans

This program is planned to be improved with future updates. The main improvements intended to be added are:  
  
* Making the console menu more user-friendly, specifically in the selection of many options, such as the selection of languages in the language filter menu.  
* Adding a couple more validations and correctly managing Exceptions that were not considered in the first version of Literalura.  
* Restructuring the Author and Book classes to create two related tables with a foreign key, instead of just two unrelated tables.  
* Making the program available in English instead of just Spanish. Possibly adding a language selection option for the program.  
  
Disclaimer: Although all these improvements are planned to be made and are expected to happen in the future, there is no scheduled date or clear timestamps. The program will be updated as the availability of my other projects allows.

