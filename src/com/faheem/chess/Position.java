package com.faheem.chess;

public class Position {
    public final int row;
    public final int col;
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean inBounds(){
        return row<8 && row>=0 && col<8 && col>=0;
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Position))return false;
        Position other = (Position) o;
        return this.row == other.row && this.col == other.col;
    }

    public int hashCode(){
        return 31*row+col;
    }



    public String toAlgebraic(){
        char fileChar = (char)('a'+this.col);
        int rank = 8-this.row;

        return fileChar+String.valueOf(rank);
    }

    public static Position fromAlgebraic(String s){
        if(s==null || s.length()!=2){
            throw new IllegalArgumentException("Invalid algebraic notation: "+s);
        }
        char[] charArray = s.toCharArray();
        char fileChar = charArray[0];
        int rank = charArray[1]-'0';

        int col = fileChar-'a';
        int row = 8-rank;

        return new Position(row,col);
    }

    public String toString(){
        return toAlgebraic();
    }

    public static void main(String[] args) {

    }




}
