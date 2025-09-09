package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> legalMoves(Board board) {
        return new ArrayList<>(); // TODO implement knight logic
    }

    @Override
    public String toString() {
        return isWhite ? "N" : "n"; // 'N' for Knight
    }
}
