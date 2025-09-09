package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> legalMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] d : dirs) {
            int r = position.row + d[0];
            int c = position.col + d[1];
            while (new Position(r, c).inBounds()) {
                Position p = new Position(r, c);
                if (isEmptyAt(board, p)) {
                    moves.add(p);
                } else {
                    if (isEnemyAt(board, p)) moves.add(p);
                    break;
                }
                r += d[0]; c += d[1];
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return isWhite ? "R" : "r";
    }
}
