
# ♟️ Java Chess Engine

A simple **object-oriented chess engine** built in Java.
Implements core chess rules: piece movement, turn handling, check, checkmate, stalemate, and pawn promotion (to queen).
This project was designed to demonstrate **OOP principles** (encapsulation, inheritance, polymorphism, abstraction) in a real-world scenario.

---

## 🚀 Features

* Full 8×8 board with initial setup
* Piece movement rules for:

    * Pawns (with double step, captures, promotion to queen)
    * Rooks, Knights, Bishops, Queen, King
* Turn handling (White always starts)
* Check detection
* Checkmate & stalemate detection
* Pawn promotion (to queen)
* Text-based display with **letters**:

    * White: `P R N B Q K`
    * Black: `p r n b q k`
* Console board rendering
* **Move format:** `from → to` (e.g., `e2 → e4`, `g8 → f6`)

---

## 📂 Project Structure

```
src/
└── com/faheem/chess/
    ├── Main.java        # simple test/demo runner
    ├── Game.java        # interactive console game loop
    ├── Board.java       # 8x8 board with rules
    ├── Position.java    # utility for coordinates (a1..h8)
    └── pieces/
        ├── Piece.java   # abstract base class
        ├── Pawn.java
        ├── Rook.java
        ├── Knight.java
        ├── Bishop.java
        ├── Queen.java
        └── King.java
```

---

## 🛠️ Installation & Running

### Compile

```bash
javac -d out src/com/faheem/chess/*.java src/com/faheem/chess/pieces/*.java
```

### Run

```bash
java -cp out com.faheem.chess.Game
```

### Example (in IntelliJ)

IntelliJ automatically compiles into:

```
out/production/java-chess-engine/
```

So you can run:

```bash
java -cp out/production/java-chess-engine com.faheem.chess.Game
```

---

## 🎮 Example Gameplay (Scholar’s Mate)

```
1. e2 → e4   e7 → e5
2. f1 → c4   g8 → f6
3. d1 → h5   f6 → e4
4. h5 → f7#  → Checkmate
```

Console output:

```
  a b c d e f g h
8 r n b q k b n r 8
7 p p p p p p p p 7
6 . . . . . . . . 6
5 . . . . . . . . 5
4 . . . . P . . . 4
3 . . . . . . . . 3
2 P P P P . P P P 2
1 R N B Q K B N R 1
  a b c d e f g h

Checkmate! White wins.
```

---

## 📚 OOP Concepts Demonstrated

* **Encapsulation** → board, position, and piece state hidden behind methods
* **Inheritance** → `Pawn`, `Rook`, etc. extend abstract `Piece`
* **Polymorphism** → `legalMoves(Board)` implemented differently per piece
* **Abstraction** → `Piece` defines common interface for all pieces

---





