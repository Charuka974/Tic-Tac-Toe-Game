Tic Tac Toe game built using the concepts of OOP using java.

Here's a detailed description you can use for your Tic Tac Toe game README file:

---

# Tic Tac Toe Game in Java

Welcome to the **Tic Tac Toe Game** built using **Java** and **Object-Oriented Programming (OOP) concepts**! This project offers a fun and challenging gameplay experience with two different modes: Easy and Hard. The Hard mode features a highly competitive AI opponent powered by the **Minimax algorithm**, ensuring strategic and engaging gameplay.

## Features

- **Two Gameplay Modes**:
    - **Easy Mode**: The AI opponent makes random moves, making it easier to win.
    - **Hard Mode**: The AI uses the **Minimax algorithm** to play optimally, providing a more challenging experience.

- **Human vs. AI Gameplay**: Play against the computer in either difficulty mode.

- **Clean and Modular Design**: Built with OOP principles, ensuring modular and maintainable code.

- **Intelligent Game Logic**: Efficient handling of game states, moves, and winner detection.

## How It Works

### Game Rules

- The game is played on a 3x3 grid.
- Players take turns marking a square with either "X" (Player) or "O" (AI).
- The first player to align three marks in a row, column, or diagonal wins.
- If all squares are filled and no player has won, the game ends in a draw.

### AI Player (Minimax Algorithm)

- In **Hard Mode**, the AI uses the **Minimax algorithm** to evaluate all possible moves and choose the optimal move for maximizing its chances of winning or minimizing the opponent's chance of winning.

- The algorithm ensures:
    - The AI never loses if the player plays perfectly.
    - Thoughtful and strategic responses to player moves.

## Installation and Usage

1. **Clone the Repository**:
   ```bash
   git clone <repository_url>
   cd tic-tac-toe-java
   ```

2. **Compile the Source Code**:
   ```bash
   javac TicTacToe.java
   ```

3. **Run the Game**:
   ```bash
   java TicTacToe
   ```

## OOP Concepts Used

- **Encapsulation**: Game logic is encapsulated within classes, promoting separation of concerns.
- **Inheritance**: Mode-specific behaviors are structured using inheritance.
- **Polymorphism**: Methods are overridden to adapt to different gameplay modes.
- **Abstraction**: Key components like `Player`, `AI`, and `GameBoard` are abstracted for modular design.

## Classes and Structure

- **Main Class**: Entry point for the game.
- **GameBoard**: Manages the game grid and checks for winning conditions.
- **Player and AIPlayer**: Represents human and AI players.
- **GameLogic**: Implements the core game flow and decision-making for AI moves.
- **Minimax Algorithm**: A dedicated method for hard-mode AI calculations.

## Example Gameplay

```
Choose Difficulty:
1 - Easy
2 - Hard
Enter your move (row and column): 1 1
Player X plays (1, 1)
AI O plays (2, 2)
...
```

## Contributing

Contributions, suggestions, and improvements are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

This description should cover the essential details and structure for your README, making it clear and helpful for users and contributors alike.