# ToysSpring

ToysSpring is a modular project designed to provide a robust backend system for a toy-related web application. The project is split into two main modules: toysApi and frontMenu. The toysApi module handles all backend functionalities, while the frontMenu module is responsible for serving JSON responses to dynamically construct the web interface.

# Modules

## 1. toysApi

The 'toysApi' module serves as the backbone of the project, providing a comprehensive backend solution. Here are some key features and technologies used:

- **Spring Boot:** Leveraging the power of the Spring framework to create a scalable and efficient backend.
- **Lombok:** Streamlining code development by reducing boilerplate code through annotation-based features.
- **Hibernate 6.3.1 with JPA:** Utilizing Hibernate for seamless database interaction through the Java Persistence API (JPA).
- **MySQL Database:** Storing and managing data in a MySQL database for persistence.
- **application.yaml:** Instead of the traditional 'application.properties', the project uses YAML configuration for improved readability and flexibility.
- **MVC Architecture:** Adopting the Model-View-Controller architecture to enhance modularity and maintainability.
- **Rest API with Authentication:** Implementing a RESTful API with authentication features for secure operations.
- **JWT Access Tokens:** Employing JSON Web Tokens for secure and stateless communication.

## 2. frontMenu

The 'frontMenu' module focuses on providing JSON responses to dynamically construct the web interface. It acts as the interface between the frontend and the 'toysApi' module, ensuring smooth communication and data presentation.

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository:

   ```bash
    git clone https://github.com/Dexpomar32/ToysSpring.git
   ```

2. Navigate to the project directory:

  ```
    cd ToysSpring
  ```

3. Configure the 'application.yaml' file with your database and application settings.

4. Build and run the project using your preferred IDE or using the command line:

   ```
    ./mvnw spring-boot:run
   ```

or

  ```
    mvn spring-boot:run
  ```

## License

This project is licensed under the [MIT License](LICENSE), making it open and free for anyone to use and modify.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve ToysSpring.
