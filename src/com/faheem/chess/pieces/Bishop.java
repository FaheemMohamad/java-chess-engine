package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> legalMoves(Board board) {
        return new ArrayList<>(); // TODO implement bishop logic
    }

    @Override
    public String toString() {
        return isWhite ? "B" : "b";
    }
}
