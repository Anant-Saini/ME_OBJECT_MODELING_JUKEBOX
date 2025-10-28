
# 🎵 Jukebox - Music Playlist Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)

## 📋 Project Overview

Jukebox is a sophisticated music playlist management system built using object-oriented design principles in Java. The application allows users to create, manage, and play custom playlists with an intuitive command-based interface. This project demonstrates clean architecture, command pattern implementation, and robust exception handling.

## ✨ Features

- **User Management**: Create and manage multiple user profiles
- **Playlist Operations**: Create, modify, delete, and play playlists
- **Song Management**: Load songs from CSV, search and play individual tracks
- **Command Pattern**: Extensible command-based architecture for easy feature addition
- **Data Persistence**: Load song data from CSV files
- **Exception Handling**: Comprehensive custom exceptions for better error management

## 🏗️ Architecture

The project follows a layered architecture with clear separation of concerns:

### Core Packages

```
com.crio.jukebox/
├── appConfig/          # Application configuration
│   └── AppConfig.java
├── commands/           # Command pattern implementations
│   ├── ICommand.java
│   ├── CommandInvoker.java
│   ├── CreateUserCommand.java
│   ├── CreatePlayListCommand.java
│   ├── ModifyPlayListCommand.java
│   ├── DeletePlayListCommand.java
│   ├── PlayPlayListCommand.java
│   ├── PlaySongCommand.java
│   └── LoadDataCommand.java
├── entities/           # Domain models
│   ├── BaseEntity.java
│   ├── User.java
│   ├── Song.java
│   ├── PlayList.java
│   └── PlayListStatus.java
├── repositories/       # Data access layer
│   ├── CRUDRepository.java
│   ├── UserRepository.java
│   ├── SongRepository.java
│   └── PlayListRepository.java
├── services/          # Business logic layer
│   ├── UserService.java
│   ├── SongService.java
│   └── PlayListService.java
└── exceptions/        # Custom exceptions
    ├── UserNotFoundException.java
    ├── SongNotFoundException.java
    ├── PlayListNotFoundException.java
    ├── PlayListIsEmptyException.java
    ├── ActivePlayListNotFoundException.java
    ├── SongNotFoundInPlayListException.java
    ├── CommandNotFoundException.java
    ├── InvalidCommandException.java
    └── CSVFileLoadingException.java
```

## 🎯 Key Components

### Commands

Implements the **Command Design Pattern** for extensible functionality:

- `CreateUserCommand` - Creates new user profiles
- `CreatePlayListCommand` - Creates custom playlists
- `ModifyPlayListCommand` - Add/remove songs from playlists
- `DeletePlayListCommand` - Remove playlists
- `PlayPlayListCommand` - Play songs from a playlist
- `PlaySongCommand` - Play individual songs
- `LoadDataCommand` - Load song data from CSV files

### Entities

Core domain models representing the system's data:

- `User` - User profile information
- `Song` - Song metadata (name, genre, album, artist)
- `PlayList` - Collection of songs belonging to a user
- `PlayListStatus` - Enumeration for playlist states

### Services

Business logic layer implementing core functionality:

- `UserService` - User management operations
- `SongService` - Song catalog management
- `PlayListService` - Playlist operations and playback

### Repositories

Data access layer with CRUD operations:

- Generic `CRUDRepository` interface
- Concrete implementations for Users, Songs, and Playlists

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Gradle 6.x or higher

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd jukebox
   ```
2. **Build the project**

   ```bash
   ./gradlew build
   ```
3. **Run the application**

   ```bash
   ./gradlew run
   ```

   Or run directly:

   ```bash
   java -jar build/libs/jukebox.jar
   ```

## 📖 Usage

The application supports the following commands:

### Load Data

```
LOAD-DATA <csv-file-path>
```

Load songs from a CSV file into the system.

### Create User

```
CREATE-USER <user-name>
```

Create a new user profile.

### Create Playlist

```
CREATE-PLAYLIST <user-id> <playlist-name> <song-id-1> <song-id-2> ...
```

Create a new playlist for a user with specified songs.

### Modify Playlist

```
MODIFY-PLAYLIST <playlist-id> <ADD/DELETE> <song-id>
```

Add or remove songs from an existing playlist.

### Delete Playlist

```
DELETE-PLAYLIST <user-id> <playlist-id>
```

Delete a user's playlist.

### Play Playlist

```
PLAY-PLAYLIST <user-id> <playlist-id>
```

Play songs from a specific playlist.

### Play Song

```
PLAY-SONG <user-id> <song-id>
```

Play a specific song.

## 📊 Sample Data Format

Songs CSV format:

```csv
id,name,genre,album,artist
1,Song Name,Pop,Album Name,Artist Name
```

## 🧪 Testing

Run the test suite:

```bash
./gradlew test
```

Run with coverage:

```bash
./gradlew test jacocoTestReport
```

## 🔧 Configuration

The application can be configured through the `AppConfig.java` file:

- Default data file paths
- Repository implementations
- Service configurations

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 Design Patterns Used

- **Command Pattern**: Encapsulates commands as objects for flexible execution
- **Repository Pattern**: Abstracts data access logic
- **Service Layer Pattern**: Separates business logic from presentation
- **Singleton Pattern**: Used in repository and service management

## 🎓 Learning Outcomes

This project demonstrates:

- Clean code principles and SOLID design
- Object-oriented programming best practices
- Design pattern implementation
- Layered architecture design
- Exception handling strategies
- CSV data processing
- Command-line interface design

## 📄 License

This project is part of Crio.Do's learning platform.

## 👥 Authors

Developed as part of the Object Modeling module at Crio.Do

## 🙏 Acknowledgments

- Crio.Do for the project framework
- Java community for best practices guidance

---

**Built with ❤️ using Java and Gradle**
=====================================

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

| Command              | Description            | Example                                       |
| -------------------- | ---------------------- | --------------------------------------------- |
| `CREATE_USER`      | Create a new user      | `CREATE_USER Ross 1500`                     |
| `CREATE_QUESTION`  | Create a new question  | `CREATE_QUESTION BinarySearch LOW 100`      |
| `CREATE_CONTEST`   | Create a new contest   | `CREATE_CONTEST JuneContest LOW Ross Q1,Q2` |
| `LIST_CONTEST`     | List contests by level | `LIST_CONTEST LOW`                          |
| `ATTEND_CONTEST`   | Register for a contest | `ATTEND_CONTEST C1 Ross`                    |
| `RUN_CONTEST`      | Participate in contest | `RUN_CONTEST C1 Ross`                       |
| `LEADERBOARD`      | Display leaderboard    | `LEADERBOARD ASC`                           |
| `WITHDRAW_CONTEST` | Withdraw from contest  | `WITHDRAW_CONTEST C1 Ross`                  |

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
