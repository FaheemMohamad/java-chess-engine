package com.faheem.chess.pieces;

import com.faheem.chess.Position;
import com.faheem.chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite, Position position) {
        super(isWhite, position);
    }

    @Override
    public List<Position> legalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int dir = isWhite ? -1 : 1; // white moves up (row-1), black down (row+1)
        int startRow = isWhite ? 6 : 1;

        // one square forward
        Position oneStep = new Position(position.row + dir, position.col);
        if (oneStep.inBounds() && isEmptyAt(board, oneStep)) {
            moves.add(oneStep);

            // two squares forward from starting row
            Position twoStep = new Position(position.row + 2 * dir, position.col);
            if (position.row == startRow && isEmptyAt(board, twoStep)) {
                moves.add(twoStep);
            }
        }

        // capture diagonals
        int[] dc = {-1, 1};
        for (int d : dc) {
            Position diag = new Position(position.row + dir, position.col + d);
            if (diag.inBounds() && isEnemyAt(board, diag)) {
                moves.add(diag);
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return isWhite ? "P" : "p";
    }
}
