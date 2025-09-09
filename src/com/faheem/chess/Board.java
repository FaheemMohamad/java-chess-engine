package com.faheem.chess;

import com.faheem.chess.pieces.Piece;

public class Board {
    private final Piece[][] grid; // 8x8 board

    public Board() {
        grid = new Piece[8][8];
    }

    public Piece getPieceAt(Position pos) {
        if (pos == null || !pos.inBounds()) {
            throw new IllegalArgumentException("Invalid position: " + pos);
        }
        return grid[pos.row][pos.col];
    }

    public void setPieceAt(Position pos, Piece piece) {
        if (pos == null || !pos.inBounds()) {
            throw new IllegalArgumentException("Invalid position: " + pos);
        }
        grid[pos.row][pos.col] = piece;
    }

    public void movePiece(Position from, Position to) {
        if (from == null || to == null || !from.inBounds() || !to.inBounds()) {
            throw new IllegalArgumentException("Invalid move: " + from + " -> " + to);
        }
        Piece moving = getPieceAt(from);
        if (moving == null) {
            throw new IllegalStateException("No piece at: " + from);
        }
        setPieceAt(to, moving);   // capture if needed
        setPieceAt(from, null);   // clear source
        moving.setPosition(to);   // update piece state
    }

    public void printBoard() {
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                System.out.print((p == null ? "." : p.toString()) + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (char f = 'a'; f <= 'h'; f++) System.out.print(f + " ");
        System.out.println();
    }

    public void setupInitial() {
        throw new UnsupportedOperationException("setupInitial not implemented yet");
    }

    public Board shallowCopy() {
        Board b = new Board();
        for (int r = 0; r < 8; r++) {
            System.arraycopy(this.grid[r], 0, b.grid[r], 0, 8);
        }
        return b;
    }

    public void clear() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c] = null;
            }
        }
    }
}
