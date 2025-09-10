package com.faheem.chess;

import java.util.Scanner;

public class Game {
    private final Board board;

    public Game() {
        board = new Board();
        board.setupInitial();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Console Chess!");
        board.printBoard();

        while (true) {
            String turn = board.isWhiteToMove() ? "White" : "Black";
            System.out.print(turn + " to move (e.g., e2 e4 or 'quit'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Game ended.");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Use format: e2 e4");
                continue;
            }

            try {
                Position from = Position.fromAlgebraic(parts[0]);
                Position to = Position.fromAlgebraic(parts[1]);
                board.movePiece(from, to);
                board.printBoard();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
