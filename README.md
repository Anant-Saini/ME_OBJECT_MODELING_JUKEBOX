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
