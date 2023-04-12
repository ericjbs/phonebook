# Phonebook

Phonebook is a phonebook application developed with Vue.js as the front-end and Spring Boot as the back-end. This repository contains the complete source code of the application.

## Prerequisites

Before getting started, you will need to have the following installed on your machine:

- [Node.js](https://nodejs.org/) (v14 or higher)
- [Vue CLI](https://cli.vuejs.org/) (v3 or higher)
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (v11 or higher)
- [Spring Boot](https://spring.io/projects/spring-boot) (v2.5 or higher)
- [MySQL](https://www.mysql.com/) (v5.7 or higher)

## Front-end Setup

To set up and run the front-end of the application, follow the steps below:

1. Open a terminal and navigate to the `phonebook-front` directory in the project.
2. Install Node.js dependencies by running the command `npm install`.
3. Start the Vue.js application by running the command `npm run serve` (you can also run other commands, as seen [here](./phonebook-front/README.md)).
4. Open a web browser and access `http://localhost:8080/` to view the running front-end application.

## Back-end Setup

To set up and run the back-end of the application, follow the steps below:

1. Open a terminal and navigate to the `phonebook-back` directory in the project.
2. Import the project into your preferred IDE as a Maven project.
3. Configure the MySQL database by updating the connection settings in the `application.properties` file.
4. Run the Spring Boot application.

You can see a complete open api specification [here](./phonebook-back/phonebook/documentation.yaml).

## Database Setup

To set up and run the database of the application, run the script present at phonebook-database directory.

## Documentation

The complete documentation of the application, including the description of API endpoints, is available in the `API_DOCUMENTATION.md` file in this repository.

## Contribution

If you wish to contribute to the development of this application, feel free to open an issue or submit a pull request. We would be happy to receive your contribution!

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
