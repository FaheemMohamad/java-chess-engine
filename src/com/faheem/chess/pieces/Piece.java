package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;

import java.util.List;

public abstract class Piece {
    // common attributes
    protected Position position;   // current square on the board
    protected final boolean isWhite;   // true = White, false = Black
    protected boolean hasMoved;    // tracks first move (important for pawns, castling)

    // constructor
    public Piece(boolean isWhite, Position position) {
        this.isWhite = isWhite;
        this.position = position;
        this.hasMoved = false;
    }

    // abstract method each subclass must implement
    public abstract List<Position> legalMoves(Board board);

    // getters
    public Position getPosition() {
        return position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    // setters (used when a move happens)
    public void setPosition(Position newPos) {
        this.position = newPos;
        this.hasMoved = true;
    }

    // helper: check if target square has enemy piece
    protected boolean isEnemyAt(Board board, Position pos) {
        if (!pos.inBounds()) return false;
        Piece target = board.getPieceAt(pos);
        return target != null && target.isWhite != this.isWhite;
    }

    // helper: check if target square is empty
    protected boolean isEmptyAt(Board board, Position pos) {
        if (!pos.inBounds()) return false;
        return board.getPieceAt(pos) == null;
    }

    // string representation: later subclasses will override for piece symbols
    @Override
    public String toString() {
        return isWhite ? "?" : "?"; // subclasses will override with actual symbols (P, R, etc.)
    }
}
