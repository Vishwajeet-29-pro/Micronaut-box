# Micronaut Box

A **modular Micronaut project** designed to explore, implement, and master various functionalities and database integrations. Each module is self-contained and focuses on a specific feature, built with **Test-Driven Development (TDD)** and **Behavior-Driven Development (BDD)** at its core. This project serves as a learning and demonstration platform for best practices in Micronaut development.

---

## Project Structure

```graphql
micronaut-box/
├── settings.gradle             # Gradle settings for module management
├── build.gradle                # Root Gradle configuration
├── README.md                   # Project documentation
├── Makefile                    # Simplified build and run commands
├── app-db-h2-connection/       # Module for H2 database integration
├── app-db-postgres-connection/ # Module for PostgreSQL integration
├── app-simple-micronaut-impl/  # Simple Micronaut application module
├── app-(future modules...)     # Additional modules for extended functionalities
```

---

## Purpose and Philosophy

### Key Goals:

1. **Modular Design**:
    - Each module is independent and focuses on a specific feature or database.
    - Enables easy experimentation and integration of new ideas.
2. **TDD and BDD Development**:
    - Writing tests first to drive the implementation.
    - Ensures reliable, maintainable, and bug-free code.
3. **Learning-Focused**:
    - A sandbox for learning Micronaut features, database integrations, and advanced concepts.

---

## Current Modules

### **1. app-db-h2-connection**

- **Purpose**: Demonstrates integration with H2, an in-memory database.
- **Features**:
    - Quick prototyping and testing with in-memory persistence.
    - RESTful APIs for CRUD operations.
    - End-to-end tests for each feature.

### **2. app-db-postgres-connection**

- **Purpose**: Demonstrates integration with PostgreSQL.
- **Features**:
    - Production-grade persistence with PostgreSQL.
    - Proper connection pooling and configuration.
    - RESTful APIs and corresponding test cases.

### **3. app-simple-micronaut-impl**

- **Purpose**: A simple Micronaut application to showcase core framework capabilities.
- **Features**:
    - Basic functionality using Micronaut's core features.
    - Provides a foundation for extending to more complex use cases.

---

## Future Plans

### Planned Modules:

1. **Enhanced Security Integration**:
    - Implement JWT-based authentication and advanced authorization mechanisms.
2. **Reactive Programming Enhancements**:
    - Add R2DBC for reactive database operations.
3. **Advanced Functionalities**:
    - Explore Micronaut Data for advanced ORM features.
    - Integrate monitoring and tracing tools.

### Testing Enhancements:

- Add mutation testing with PIT (Pitest).
- Focus on integrating test automation pipelines.

---

## Getting Started

### Prerequisites

- **Java 17+** (or preferred version).
- **Gradle** (build tool).

## Makefile Integration
This project uses a Makefile to simplify build, test, run, and other operations. The Makefile provides a consistent interface for managing project tasks across different environments. For Windows users, you can install `make` using [Chocolatey](https://docs.chocolatey.org/en-us/choco/setup/#install-with-cmdexe) by running the following command:
```bash
choco install make
```

### Using the Makefile
1. Build and Test All Modules:
```bash
    make all
```
2. Build Modules:
```bash
    make
```
3. Clean All Build Files:
```bash
    make clean
```
4. Run the Specific Application: (e.g. h2 application)
```bash
    make run-h2
```
5. Build and Test a Specific Module:
```bash
    make run-module
```
6. Testing
   Run all tests for the module:
```bash
    make test
```
7. Help
```bash
    make help
```
---

## OpenAPI Integration

The project leverages **OpenAPI** to provide interactive API documentation. This makes it easy to explore and test the REST APIs for each module.

### How to Access OpenAPI Documentation:

1. Start the Micronaut application.
2. Navigate to the following URL in your browser:
```bash
http://localhost:8080/swagger-ui/index.html
```
3. Explore the API endpoints, input parameters, and responses in an interactive interface.

---

## Contributions

Contributions are welcome! Feel free to fork the repository, create a new module, or enhance the existing ones.

---

## Author

**Vishwajeet Kotkar**

- A passionate developer focused on mastering Micronaut, Test-Driven Development, and modern software practices.

Connect on [LinkedIn](https://www.linkedin.com/in/vishwajeet-kotkar/) or explore my other projects on [GitHub](https://github.com/vishwajeet-29-pro).

