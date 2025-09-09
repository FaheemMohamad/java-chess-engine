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
        List<Position> moves = new ArrayList<>();
        int[][] jumps = {
                {2,1}, {2,-1}, {-2,1}, {-2,-1},
                {1,2}, {1,-2}, {-1,2}, {-1,-2}
        };
        for (int[] j : jumps) {
            Position p = new Position(position.row + j[0], position.col + j[1]);
            if (p.inBounds() && (isEmptyAt(board, p) || isEnemyAt(board, p))) {
                moves.add(p);
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return isWhite ? "N" : "n";
    }
}
