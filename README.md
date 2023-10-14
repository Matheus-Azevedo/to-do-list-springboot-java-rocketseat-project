# To-Do List Project

## Overview

This is a To-Do List project developed using Spring Boot and Java. The project was created between October 9, 2023, and October 13, 2023, in collaboration with Rocketseat.

## Project Structure

The project is organized as follows:

- `Dockerfile`: Configuration file for Docker containerization (if applicable).
- `HELP.md`: Documentation or help file.
- `README.md`: This document.
- `estrutura_projeto.txt`: A file that might describe the project structure (not provided in detail).
- `mvnw` and `mvnw.cmd`: Maven Wrapper scripts for building the project.
- `pom.xml`: Maven project configuration file.
- `src`: Source code directory.
  - `main`: Main source code.
    - `java`: Java source code.
      - `br`: Base package.
        - `com`: Company or organization package.
          - `matheuseduardo`: Developer's package.
            - `todolist`: Project's root package.
              - `TodolistApplication.java`: The main application class.
              - `errors`: Package for handling exceptions.
                - `ExceptionHandlerController.java`: Exception handler controller.
              - `filter`: Package for filters (e.g., authentication).
                - `FilterTaskAuth.java`: Task authentication filter.
              - `task`: Package for managing tasks.
                - `ITaskRepository.java`: Task repository interface.
                - `TaskController.java`: Task controller.
                - `TaskModel.java`: Task data model.
              - `user`: Package for managing users.
                - `IUserRepository.java`: User repository interface.
                - `UserController.java`: User controller.
                - `UserModel.java`: User data model.
              - `utils`: Package for utility classes.
                - `Utils.java`: Utility methods.
    - `resources`: Configuration files and resources.
      - `application.properties`: Application configuration properties.
      - `static`: Directory for static resources (e.g., CSS, JavaScript files).
      - `templates`: Directory for templates (if using server-side rendering).
  - `test`: Test source code.
    - `java`: Java test source code.
      - `br`: Base package.
        - `com`: Company or organization package.
          - `matheuseduardo`: Developer's package.
            - `todolist`: Project's root package.
              - `TodolistApplicationTests.java`: Test class for the application.
- `target`: Compiled classes and build artifacts.
  - `classes`: Compiled class files.
  - `generated-sources` and `generated-test-sources`: Generated sources and test sources (if applicable).
  - `test-classes`: Compiled test class files.
  - `todolist.jar`: Executable JAR file (if applicable).

## Getting Started

To run the To-Do List application, follow these steps:

1.  Make sure you have Java and Maven installed on your system.
2.  Clone or download this repository.
3.  Navigate to the project directory.

### Running the Application

4.  Build the project using Maven:

    `mvn clean package`

5.  Run the application:

    `java -jar target/todolist.jar`

6.  The application should now be running locally. You can access it through a web browser or API client.

## Documentation

For more detailed information about the project's functionality, endpoints, and usage, please refer to the provided documentation or `HELP.md`.

## Contribution

Feel free to contribute to this project by submitting issues or pull requests. We welcome your input and feedback.

## License

This project is open-source and available under the [MIT License](https://chat.openai.com/c/LICENSE).
