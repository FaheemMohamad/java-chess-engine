package com.faheem.chess;

import com.faheem.chess.pieces.*;

/* Board: holds pieces, basic move/print/setup logic */
public class Board {
    private final Piece[][] grid; // 8x8 board
    private boolean whiteToMove;  // true if white's turn

    public Board() {
        grid = new Piece[8][8];
        whiteToMove = true; // white starts
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

        // check turn
        if (moving.isWhite() != whiteToMove) {
            throw new IllegalStateException("It's " + (whiteToMove ? "White" : "Black") + "'s turn");
        }

        // check basic legality
        boolean legal = false;
        for (Position p : moving.legalMoves(this)) {
            if (p.equals(to)) {
                legal = true;
                break;
            }
        }
        if (!legal) {
            throw new IllegalArgumentException("Illegal move for " + moving + ": " + from + " -> " + to);
        }

        // simulate move on copy
        Board copy = this.shallowCopy();
        Piece movingCopy = copy.getPieceAt(from);
        copy.setPieceAt(to, movingCopy);
        copy.setPieceAt(from, null);
        movingCopy.setPosition(to);

        if (copy.isInCheck(moving.isWhite())) {
            throw new IllegalArgumentException("Illegal move: would leave "
                    + (moving.isWhite() ? "White" : "Black") + " in check");
        }

        // execute real move
        setPieceAt(to, moving);
        setPieceAt(from, null);
        moving.setPosition(to);


        if (moving instanceof Pawn) {
            if ((moving.isWhite() && to.row == 0) || (!moving.isWhite() && to.row == 7)) {
                Piece promoted = new Queen(moving.isWhite(), to);
                setPieceAt(to, promoted);
                System.out.println("Pawn promoted to Queen at " + to);
            }
        }

        // switch turn
        whiteToMove = !whiteToMove;
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
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

    public boolean isInCheck(boolean white) {
        // find king position
        Position kingPos = null;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p instanceof com.faheem.chess.pieces.King && p.isWhite() == white) {
                    kingPos = new Position(r, c);
                    break;
                }
            }
        }
        if (kingPos == null) throw new IllegalStateException("No king found for " + (white ? "White" : "Black"));

        // check opponent moves
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.isWhite() != white) {
                    for (Position move : p.legalMoves(this)) {
                        if (move.equals(kingPos)) {
                            return true; // king under attack
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean hasAnyLegalMoves(boolean white) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = grid[r][c];
                if (piece != null && piece.isWhite() == white) {
                    for (Position move : piece.legalMoves(this)) {
                        // simulate on a copy
                        Board copy = this.shallowCopy();
                        Piece movingCopy = copy.getPieceAt(new Position(r, c));
                        copy.setPieceAt(move, movingCopy);
                        copy.setPieceAt(new Position(r, c), null);
                        movingCopy.setPosition(move);

                        if (!copy.isInCheck(white)) {
                            return true; // found at least one valid move
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(boolean white) {
        return isInCheck(white) && !hasAnyLegalMoves(white);
    }

    public boolean isStalemate(boolean white) {
        return !isInCheck(white) && !hasAnyLegalMoves(white);
    }

    public boolean isSquareAttacked(Position pos, boolean byWhite) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.isWhite() == byWhite) {
                    if (p instanceof com.faheem.chess.pieces.King) {
                        // Kings: check adjacent squares manually
                        int[][] dirs = {
                                {1,0}, {-1,0}, {0,1}, {0,-1},
                                {1,1}, {1,-1}, {-1,1}, {-1,-1}
                        };
                        for (int[] d : dirs) {
                            Position adj = new Position(r + d[0], c + d[1]);
                            if (adj.inBounds() && adj.equals(pos)) {
                                return true;
                            }
                        }
                    } else {
                        // All other pieces use legalMoves()
                        for (Position m : p.legalMoves(this)) {
                            if (m.equals(pos)) return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
