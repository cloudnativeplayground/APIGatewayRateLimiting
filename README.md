

# API Gateway with Rate Limiting

## Project Overview

This project implements a secure API Gateway that supports authentication, caching, and API request throttling. The focus is on **Spring Cloud Gateway**, **Redis**, and **Kubernetes Service Mesh**.

### Key Features:
- **Authentication**: Integrates with authentication mechanisms to ensure secure API access.
- **Caching**: Reduces load on backend services by caching responses.
- **API Request Throttling**: Prevents abuse and overload by applying rate limiting using Redis.
- **Tech Stack**:
    - **Spring Cloud Gateway**: For routing and API Gateway management.
    - **Redis**: For rate limiting and caching.
    - **Kubernetes Service Mesh**: For scalable and secure service communication.
    - **Docker**: Containerization for easier deployment and scaling.

## Maintainer Details

- **Maintainer**: [Aditya Pratap Bhuyan](https://linkedin.com/in/adityabhuyan)
- **GitHub Repository**: [cloudnativeplayground/APIGatewayRateLimiting](https://github.com/cloudnativeplayground/APIGatewayRateLimiting)
- **LinkedIn Profile**: [Aditya Pratap Bhuyan](https://linkedin.com/in/adityabhuyan)
- **Email**: [aditya.sunjava@gmail.com](mailto:aditya.sunjava@gmail.com) (optional)

For any issues, questions, or contributions, feel free to reach out to the maintainer via GitHub or LinkedIn.

---

## File Structure

Here is a high-level overview of the file structure:

```
API-Gateway-with-Rate-Limiting/
│
├── .gitignore               # Specifies files and directories to be ignored by Git
├── Dockerfile               # Dockerfile for building the API Gateway image
├── docker-compose.yml       # Docker Compose configuration for running services locally
├── k8s/                     # Kubernetes configurations (deployments, services, etc.)
│   ├── api-gateway-deployment.yaml  # Deployment configuration for the API Gateway
│   ├── api-gateway-service.yaml     # Service configuration for exposing API Gateway
│   ├── redis-deployment.yaml       # Deployment configuration for Redis
│   ├── redis-service.yaml          # Service configuration for exposing Redis
│   └── ingress.yaml                # Ingress configuration (optional)
├── src/                     # Source code for the Spring Boot application
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── controller/       # API Controllers
│   │   │           ├── config/           # Spring Configurations
│   │   │           ├── service/          # Business Logic (Rate Limiting, Authentication)
│   │   │           └── ApiGatewayApplication.java  # Main entry point of the Spring Boot app
│   │   └── resources/
│   │       ├── application.yml         # Main application configuration
│   │       ├── application-de.yml      # Development-specific application configuration
│   │       └── logback-spring.yml      # Logging configuration
├── target/                  # Compiled Maven artifacts (ignored in .gitignore)
├── pom.xml                  # Maven build configuration
└── README.md                # Project documentation (this file)
```

---

## Project Workflow

The project follows a typical CI/CD workflow with Docker and Kubernetes:

1. **Development**:
    - Developers work on the application code in the `src/` directory.
    - Changes to the `pom.xml` may include adding dependencies like Spring Cloud Gateway, Redis, etc.
    - Application configurations are defined in `application.yml` (and environment-specific configurations like `application-de.yml`).

2. **Build**:
    - The project is built using Maven. The build output will be a JAR file that is placed in the `target/` directory.
    - The `Dockerfile` defines how the application is packaged into a Docker image, which can be built and deployed.

3. **Testing**:
    - Unit tests and integration tests are included in the `src/test/java/` directory.
    - Tests such as `ApiGatewayTest.java` and `RateLimitingTest.java` ensure the functionality of the API Gateway and rate-limiting logic.

4. **Containerization**:
    - Docker is used to containerize the application, allowing it to run in any environment that supports Docker.
    - A `docker-compose.yml` file is included for local development and testing. It defines the services (API Gateway, Redis) and their interactions.

5. **Kubernetes Deployment**:
    - Kubernetes deployment and service configuration files are in the `k8s/` directory.
    - The API Gateway and Redis are deployed as services in a Kubernetes cluster.
    - `ingress.yaml` can be used to expose the API Gateway to external traffic.

6. **Rate Limiting**:
    - The API Gateway uses Redis to store request counts and apply rate-limiting rules.
    - The rate-limiting logic is implemented in the `RateLimitingService.java` class.

---

## Setup Instructions

### 1. **Prerequisites**
- Java 17 (or higher)
- Maven
- Docker
- Kubernetes (optional, for deployment)
- Redis (for local development, Docker will spin up Redis for you)

### 2. **Clone the Repository**
Clone the repository to your local machine:
   ```bash
   git clone https://github.com/cloudnativeplayground/APIGatewayRateLimiting.git
   cd APIGatewayRateLimiting
   ```

### 3. **Build the Application**

First, ensure you have Maven and Java 17 installed. Then build the project using Maven:
   ```bash
   ./mvnw clean package
   ```

This will generate a JAR file inside the `target/` directory.

### 4. **Run the Application with Docker Compose**

The project comes with a `docker-compose.yml` file to run the API Gateway and Redis locally. Use the following command to build and start the services:

   ```bash
   docker-compose up --build
   ```

This will:
- Build the Docker image for the API Gateway.
- Start Redis and the API Gateway in Docker containers.
- Expose the API Gateway on port `8080` and Redis on port `6379`.

### 5. **Test the API Gateway**

You can test the API Gateway by sending HTTP requests to `http://localhost:8080`. If rate limiting is configured correctly, requests to the same API should be throttled after a certain threshold.

### 6. **Deploy on Kubernetes**

To deploy on Kubernetes:
1. Ensure you have a Kubernetes cluster set up (e.g., via Minikube, GKE, or AWS EKS).
2. Apply the Kubernetes configuration files:
   ```bash
   kubectl apply -f k8s/
   ```
3. You can expose the API Gateway via an Ingress or LoadBalancer, depending on your Kubernetes setup.

---

## Docker Setup

The project includes a **Dockerfile** to containerize the API Gateway service. If you want to build and run the application manually via Docker:

1. **Build the Docker image**:
   ```bash
   docker build -t api-gateway .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -p 8080:8080 api-gateway
   ```

This will expose the application on port `8080`.

---

## Contributing

Contributions are welcome! To contribute to the project:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-name`).
6. Create a new Pull Request.

---

## License

This project is licensed under the **GNU General Public License v3.0**. See the [LICENSE](LICENSE) file for more information.

---
