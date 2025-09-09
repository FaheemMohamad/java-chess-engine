package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    // constructor
    public Pawn(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    // pawn movement logic (to be implemented later)
    @Override
    public List<Position> legalMoves(Board board) {
        return new ArrayList<>(); // placeholder
    }

    // string representation
    @Override
    public String toString() {
        return isWhite ? "P" : "p";
    }
}
