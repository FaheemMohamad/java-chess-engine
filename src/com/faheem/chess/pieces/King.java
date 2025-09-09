package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> legalMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] dirs = {
                {1,0}, {-1,0}, {0,1}, {0,-1},
                {1,1}, {1,-1}, {-1,1}, {-1,-1}
        };
        for (int[] d : dirs) {
            Position p = new Position(position.row + d[0], position.col + d[1]);
            if (p.inBounds() && (isEmptyAt(board, p) || isEnemyAt(board, p))) {
                moves.add(p);
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }
}
