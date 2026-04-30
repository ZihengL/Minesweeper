![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-4796FC?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

# MINESWEEPER

A robust, enterprise-architected clone of the classic Minesweeper game, built using modern Java and JavaFX.
Created as one of the two projects I chose to work on during a short blitz to expand my portfolio and challenge
myself in applying the totality of what I've learned to date.


![Demo](./docs/Minesweeper_Demo.gif)

## 🛠️ Tech Stack

* **Language:** Java 21
* **UI Framework:** JavaFX
* **Build Tool:** Apache Maven
* **Core Concepts:** MVC Architecture, Command Pattern, Observer Pattern, Recursion.

## 🚀 Key Architectural Features

* <span style="font-size:1.2em;">**Algorithmic User Experience:**</span> Designed a deferred, parameter-driven matrix generation algorithm that computes mine placements relative to the user's initial input, guaranteeing a safe first interaction.


* <span style="font-size:1.2em;">**Optimized Presentation Layer:**</span> Despite the fact that it could run on a toaster, I committed to designing a highly efficient UI using a granular 1:1 Observer pattern. Logical mutations trigger isolated, single-cell UI updates rather than costly full-grid re-renders, ensuring smooth performance even on the Expert difficulty (16x30 grid).


* <span style="font-size:1.2em;">**Dynamic UI Regeneration:**</span> Leveraged the Command pattern to seamlessly manage game state transitions. The interface and matrix dynamically regenerate and resize on the fly to support varying difficulty dimensions (Beginner, Intermediate, Expert).


* <span style="font-size:1.2em;">**Efficient Matrix Traversal:**</span> Implemented a recursive flood-fill algorithm that fluidly unveils safe zones and navigates complex 2D matrix structures.


* <span style="font-size:1.2em;">**Strict Decoupling:**</span> Complete separation of the core game logic from the rendering layer through functional interfaces and event handling.

<br>
<p>
  <img src="src/main/resources/io/github/zihengl/demineur/images/Frame_BURIED.jpg" width="75" />
  <img src="src/main/resources/io/github/zihengl/demineur/images/Frame_FLAGGED.jpg" width="75" />
  <img src="src/main/resources/io/github/zihengl/demineur/images/Frame_0.jpg" width="75" />
  <img src="src/main/resources/io/github/zihengl/demineur/images/Frame_-1.jpg" width="75" />
</p>

## 🎮 How to Run (Pre-compiled Release)

> <span style="font-size:1.15em;"><span style="color:yellow">**IMPORTANT**</span>: You must have **Java 21** (or higher) installed on your machine to run this application.
> [**The latest version of Java Temurin can be found here.**](https://adoptium.net/temurin/releases)</span>

1. Navigate to the [Releases](../../releases) page of this repository.
2. Download the latest `Minesweeper-v1.0.jar` file.
3. Run the .jar file and enjoy! :)