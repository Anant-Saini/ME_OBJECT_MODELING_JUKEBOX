# 🎮 ME_OBJECT_MODELING_V2_MODULE_CONTROLLER

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-7.x-brightgreen.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> A robust command-driven coding game platform implementing modern object-oriented design patterns and best practices.

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Architecture](#-architecture)
- [Setup Instructions](#-setup-instructions)
- [Usage](#-usage)
- [Folder Structure](#-folder-structure)
- [Sample Code](#-sample-code)
- [Commands](#-commands)
- [Contributing](#-contributing)
- [License](#-license)

## 🎯 Overview

ME_OBJECT_MODELING_V2_MODULE_CONTROLLER is a Java-based application that demonstrates advanced object modeling concepts through a coding game platform. The project showcases clean architecture principles, command pattern implementation, and separation of concerns.

### What This Project Does

- **User Management**: Register and manage users with different skill levels
- **Contest Management**: Create and manage coding contests with varying difficulty levels
- **Question Management**: Handle coding questions with different difficulty ratings
- **Leaderboard System**: Track and display user performance
- **Command-Driven Interface**: Execute operations through a flexible command pattern

## ✨ Key Features

- 🏗️ **Clean Architecture**: Follows separation of concerns with distinct layers (entities, repositories, services, commands)
- 🎯 **Command Pattern**: Extensible command-based operation system
- 💾 **In-Memory Repository**: Fast data access with repository pattern implementation
- 🔧 **Dependency Injection**: Loose coupling through constructor-based DI
- 📊 **Data Transfer Objects**: Clean data flow between layers
- 🧪 **Test Coverage**: Comprehensive unit tests for all components
- 📝 **Type Safety**: Strong typing with Java generics

## 🏛️ Architecture

The application follows a layered architecture:

```
┌─────────────────────────────────────┐
│         Application Layer           │
│         (App.java)                  │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Command Layer               │
│    (Command Pattern Implementation) │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Service Layer               │
│    (Business Logic)                 │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Repository Layer            │
│    (Data Access)                    │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Entity Layer                │
│    (Domain Models)                  │
└─────────────────────────────────────┘
```

## 🚀 Setup Instructions

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Gradle**: Version 7.x or higher (or use the included wrapper)
- **Git**: For cloning the repository

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd anantsaini-india-ME_OBJECT_MODELING_V2
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run tests**
   ```bash
   ./gradlew test
   ```

4. **Run the application**
   ```bash
   ./gradlew run
   ```

   Or alternatively:
   ```bash
   java -jar build/libs/ME_OBJECT_MODELING_V2.jar
   ```

## 💻 Usage

### Running the Application

The application reads commands from `input.txt` file:

```bash
./gradlew run < input.txt
```

### Input Format

The application accepts commands in the following format:

```
CREATE_USER <name> <score>
CREATE_QUESTION <title> <level> <score>
CREATE_CONTEST <name> <level> <creator> <questionIds>
LIST_CONTEST <level>
ATTEND_CONTEST <contestId> <userName>
RUN_CONTEST <contestId> <userName>
LEADERBOARD <order>
```

### Example Usage

```bash
CREATE_USER Ross 1500
CREATE_USER Monica 1800
CREATE_QUESTION Binary_Search LOW 100
CREATE_CONTEST June_Challenge LOW Ross Q1,Q2
LIST_CONTEST LOW
```

## 📁 Folder Structure

```
anantsaini-india-ME_OBJECT_MODELING_V2/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── crio/
│   │               └── codingame/
│   │                   ├── App.java                    # Application entry point
│   │                   ├── appConfig/                  # Application configuration
│   │                   ├── commands/                   # Command implementations
│   │                   │   ├── CreateUserCommand.java
│   │                   │   ├── CreateQuestionCommand.java
│   │                   │   ├── CreateContestCommand.java
│   │                   │   └── ...
│   │                   ├── dtos/                       # Data Transfer Objects
│   │                   ├── entities/                   # Domain models
│   │                   │   ├── User.java
│   │                   │   ├── Question.java
│   │                   │   ├── Contest.java
│   │                   │   └── ...
│   │                   ├── exceptions/                 # Custom exceptions
│   │                   ├── repositories/               # Data access layer
│   │                   │   ├── IUserRepository.java
│   │                   │   ├── UserRepository.java
│   │                   │   └── ...
│   │                   └── services/                   # Business logic
│   │                       ├── UserService.java
│   │                       ├── ContestService.java
│   │                       └── ...
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── crio/
│                   └── codingame/                     # Unit tests
│
├── build.gradle                                       # Build configuration
├── gradlew                                            # Gradle wrapper (Unix)
├── gradlew.bat                                        # Gradle wrapper (Windows)
├── input.txt                                          # Sample input file
├── songs.csv                                          # Sample data file
└── README.md                                          # This file
```

## 📝 Sample Code

### Creating a User

```java
// Using the UserService
UserService userService = new UserService(userRepository);
User user = userService.create("John Doe", 1500);
System.out.println("Created user: " + user.getName());
```

### Creating a Contest

```java
// Using the ContestService
ContestService contestService = new ContestService(
    contestRepository,
    questionRepository,
    userRepository
);

Contest contest = contestService.create(
    "Summer Challenge",
    Level.MEDIUM,
    "creator123",
    Arrays.asList("Q1", "Q2", "Q3")
);
```

### Executing Commands

```java
// Command pattern implementation
CommandInvoker invoker = new CommandInvoker();
CreateUserCommand command = new CreateUserCommand(userService);
invoker.executeCommand("CREATE_USER Alice 2000", command);
```

## 🎮 Commands

| Command | Description | Example |
|---------|-------------|----------|
| `CREATE_USER` | Create a new user | `CREATE_USER Ross 1500` |
| `CREATE_QUESTION` | Create a new question | `CREATE_QUESTION BinarySearch LOW 100` |
| `CREATE_CONTEST` | Create a new contest | `CREATE_CONTEST JuneContest LOW Ross Q1,Q2` |
| `LIST_CONTEST` | List contests by level | `LIST_CONTEST LOW` |
| `ATTEND_CONTEST` | Register for a contest | `ATTEND_CONTEST C1 Ross` |
| `RUN_CONTEST` | Participate in contest | `RUN_CONTEST C1 Ross` |
| `LEADERBOARD` | Display leaderboard | `LEADERBOARD ASC` |
| `WITHDRAW_CONTEST` | Withdraw from contest | `WITHDRAW_CONTEST C1 Ross` |

## 🤝 Contributing

We welcome contributions to improve the project! Here's how you can help:

### Getting Started

1. **Fork the repository**
   ```bash
   git fork <repository-url>
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make your changes**
   - Write clean, documented code
   - Follow existing code style and patterns
   - Add tests for new functionality

4. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```

5. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```

6. **Open a Pull Request**

### Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable and method names
- Write JavaDoc comments for public methods
- Keep methods focused and concise
- Maintain test coverage above 80%

### Testing

Ensure all tests pass before submitting:

```bash
./gradlew test
./gradlew build
```

## 📄 License

This project is part of the Crio.Do learning platform.

## 📞 Contact & Support

- **Issues**: Please report bugs and issues through the GitLab issue tracker
- **Questions**: Reach out through the Crio.Do platform

## 🙏 Acknowledgments

- Built as part of the Crio.Do ME_OBJECT_MODELING_V2 module
- Special thanks to all contributors and maintainers

---

**Made with ❤️ by Crio.Do Learners**

*Last Updated: October 2025*
