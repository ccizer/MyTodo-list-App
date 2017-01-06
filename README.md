# Todo List Application

Todo list application for creating new lists and adding new steps to follow.
Users must be registered first and then can login to the system. After login, the user can create or delete todo lists and add new steps or deletes some of them from the selected todo list. By clicking on each step, the user can check the step as completed with successful.

# Prerequisites

This application was developed with the usage of the technologies below;

* Java 8
* Spring Boot
* MySQL
* Hibernate JPA, Spring Data
* Thymeleaf
* Maven
* Mockito
* Bootstrap

## Running the tests

Three test cases were created by using mockito. Test cases provides to see the validation of the user registration, list and step creation when the parameters are set. Also, by not setting some parameters, error messages are planned to be seen on purpose.

* UserRegistrationTest
* ListDefinitionTest
* StepDefinitionTest

## Author

Can Çizer

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
