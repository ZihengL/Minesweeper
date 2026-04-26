![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-4796FC?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A robust, enterprise-architected clone of the classic Minesweeper game, built using modern Java and JavaFX.

This project goes beyond a simple recreation of the game; it serves as a practical demonstration of strict adherence to clean software architecture, Object-Oriented Programming principles, and Gang of Four design patterns.

*(Insert screenshot or GIF of gameplay here)*
## 🚀 Key Architectural Features

* **Algorithmic User Experience:** Designed a deferred, parameter-driven matrix generation algorithm that computes mine placements relative to the user's initial input, guaranteeing a safe first interaction.
* **Optimized Presentation Layer:** Engineered a highly efficient UI using a granular 1:1 Observer pattern. Logical mutations trigger isolated, single-cell UI updates rather than costly full-grid re-renders, ensuring smooth performance even on the Expert difficulty (16x30 grid).
* **Dynamic UI Regeneration:** Leveraged the Command pattern to seamlessly manage game state transitions. The interface and matrix dynamically regenerate and resize on the fly to support varying difficulty dimensions (Beginner, Intermediate, Expert).
* **Efficient Matrix Traversal:** Implemented a highly optimized recursive flood-fill algorithm that fluidly unveils safe zones and navigates complex 2D matrix structures.
* **Strict Decoupling:** Complete separation of the core game logic from the rendering layer through functional interfaces and event handling.

## 🛠️ Tech Stack

* **Language:** Java 21
* **UI Framework:** JavaFX
* **Build Tool:** Apache Maven
* **Core Concepts:** MVC Architecture, Command Pattern, Observer Pattern, Recursion.

## 🎮 How to Run (Pre-compiled Release)

**System Requirement:** You must have **Java 21** (or higher) installed on your machine to run this application.

1. Navigate to the [Releases](../../releases) page of this repository.
2. Download the latest `Minesweeper-v1.0.jar` file.
3. 