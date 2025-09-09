package com.faheem.chess;

import com.faheem.chess.pieces.*;

/* Board: holds pieces, basic move/print/setup logic */
public class Board {
    private final Piece[][] grid; // 8x8 board

    public Board() {
        grid = new Piece[8][8];
    }

    public Piece getPieceAt(Position pos) {
        if (pos == null || !pos.inBounds()) throw new IllegalArgumentException("Invalid position: " + pos);
        return grid[pos.row][pos.col];
    }

    public void setPieceAt(Position pos, Piece piece) {
        if (pos == null || !pos.inBounds()) throw new IllegalArgumentException("Invalid position: " + pos);
        grid[pos.row][pos.col] = piece;
    }

    public void movePiece(Position from, Position to) {
        if (from == null || to == null || !from.inBounds() || !to.inBounds())
            throw new IllegalArgumentException("Invalid move: " + from + " -> " + to);
        Piece moving = getPieceAt(from);
        if (moving == null) throw new IllegalStateException("No piece at: " + from);
        setPieceAt(to, moving);   // capture if present
        setPieceAt(from, null);   // clear source
        moving.setPosition(to);   // update piece state
    }

    public void printBoard() {
        // print file labels at top
        System.out.print("  ");
        for (char f = 'a'; f <= 'h'; f++) System.out.print(f + " ");
        System.out.println();

        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " "); // left rank
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                System.out.print((p == null ? "." : p.toString()) + " ");
            }
            System.out.println((8 - r)); // right rank
        }

        // print file labels at bottom
        System.out.print("  ");
        for (char f = 'a'; f <= 'h'; f++) System.out.print(f + " ");
        System.out.println();
    }


    public void setupInitial() {
        clear(); // empty board first

        // black pawns (row 1)
        for (int c = 0; c < 8; c++) setPieceAt(new Position(1, c), new Pawn(false, new Position(1, c)));

        // black back rank (row 0): R N B Q K B N R
        setPieceAt(new Position(0, 0), new Rook(false, new Position(0, 0)));
        setPieceAt(new Position(0, 1), new Knight(false, new Position(0, 1)));
        setPieceAt(new Position(0, 2), new Bishop(false, new Position(0, 2)));
        setPieceAt(new Position(0, 3), new Queen(false, new Position(0, 3)));
        setPieceAt(new Position(0, 4), new King(false, new Position(0, 4)));
        setPieceAt(new Position(0, 5), new Bishop(false, new Position(0, 5)));
        setPieceAt(new Position(0, 6), new Knight(false, new Position(0, 6)));
        setPieceAt(new Position(0, 7), new Rook(false, new Position(0, 7)));

        // white pawns (row 6)
        for (int c = 0; c < 8; c++) setPieceAt(new Position(6, c), new Pawn(true, new Position(6, c)));

        // white back rank (row 7): R N B Q K B N R
        setPieceAt(new Position(7, 0), new Rook(true, new Position(7, 0)));
        setPieceAt(new Position(7, 1), new Knight(true, new Position(7, 1)));
        setPieceAt(new Position(7, 2), new Bishop(true, new Position(7, 2)));
        setPieceAt(new Position(7, 3), new Queen(true, new Position(7, 3)));
        setPieceAt(new Position(7, 4), new King(true, new Position(7, 4)));
        setPieceAt(new Position(7, 5), new Bishop(true, new Position(7, 5)));
        setPieceAt(new Position(7, 6), new Knight(true, new Position(7, 6)));
        setPieceAt(new Position(7, 7), new Rook(true, new Position(7, 7)));
    }

    public Board shallowCopy() {
        Board b = new Board();
        for (int r = 0; r < 8; r++) System.arraycopy(this.grid[r], 0, b.grid[r], 0, 8);
        return b;
    }

    public void clear() {
        for (int r = 0; r < 8; r++) for (int c = 0; c < 8; c++) grid[r][c] = null;
    }
}
