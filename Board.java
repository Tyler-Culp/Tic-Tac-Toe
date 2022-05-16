import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Board{
    public final String openSpace = "*";
    public final String X = "X";
    public final String O = "O";
    public int emptySpaces = 9;
    public String[][] board;

    public Board(){
        this.board = new String[3][3];
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                this.board[i][j] = openSpace;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < this.board.length; row++){
            for (int col = 0; col < this.board[row].length; col++){
                System.out.printf(this.board[row][col]);
            }
            System.out.println("");
        }
    }

    public boolean canPutThere(int row, int col){
        if(row < 0 || row > 2 || col < 0 || col >2){
            return false;
        }

        if(this.board[row][col].equals(openSpace)){
            return true;
        }

        return false;
    }

    public boolean winCondition(){
        int topRowCountX = 0;
        int middleRowCountX = 0;
        int bottomRowCountX = 0;
        int topRowCountO = 0;
        int middleRowCountO = 0;
        int bottomRowCountO = 0;

        int leftColCountX = 0;
        int middleColCountX = 0;
        int rightColCountX = 0;
        int leftColCountO = 0;
        int middleColCountO = 0;
        int rightColCountO = 0;
        for(int i = 0; i < this.board.length; i++){
            if(this.board[0][i].equals(X)){
                topRowCountX++;
            }

            if(this.board[1][i].equals(X)){
                middleRowCountX++;
            }

            if(this.board[2][i].equals(X)){
                bottomRowCountX++;
            }

            if(this.board[0][i].equals(O)){
                topRowCountO++;
            }

            if(this.board[1][i].equals(O)){
                middleRowCountO++;
            }

            if(this.board[2][i].equals(O)){
                bottomRowCountO++;
            }

            if(this.board[i][0].equals(X)){
                leftColCountX++;
            }

            if(this.board[i][1].equals(X)){
                middleColCountX++;
            }

            if(this.board[i][2].equals(X)){
                rightColCountX++;
            }

            if(this.board[i][0].equals(O)){
                leftColCountO++;
            }

            if(this.board[i][1].equals(O)){
                middleColCountO++;
            }

            if(this.board[i][2].equals(O)){
                rightColCountO++;
            }
        }

        int leftDiagnolO = 0;
        int leftDiagnolX = 0;
        int rightDiagnolO = 0;
        int rightDiagnolX = 0;

        for(int i = 0; i < this.board.length; i++){
            if(this.board[i][i].equals(X)){
                leftDiagnolX++;
            }

            if(this.board[i][i].equals(O)){
                leftDiagnolO++;
            }
        }

        for(int i = 0; i < this.board.length; i++){
            if(this.board[i][2-i].equals(X)){
                rightDiagnolX++;
            }

            if(this.board[i][2-i].equals(O)){
                rightDiagnolO++;
            }
        }

        if(topRowCountX == 3 || middleRowCountX == 3 || bottomRowCountX == 3
        || leftColCountX == 3 || middleColCountX == 3 || rightColCountX == 3
        || leftDiagnolX == 3 || rightDiagnolX == 3){
            //System.out.println("X's win!");
            return true;
        }

        if(topRowCountO == 3 || middleRowCountO == 3 || bottomRowCountO == 3
        || leftColCountO == 3 || middleColCountO == 3 || rightColCountO == 3
        || leftDiagnolO == 3 || rightDiagnolO == 3){
            //System.out.println("O's win!");
            return true;
        }

        return false;
    }

    public void set_O(int row, int col){
        if(canPutThere(row, col)){
            this.board[row][col] = O;
            this.emptySpaces--;
            this.printBoard();
        }

        else{
            System.out.println("Please choose a different spot");
        }
        this.winCondition();
    }

    public void set_X(int row, int col){
        if(canPutThere(row, col)){
            this.board[row][col] = X;
            this.emptySpaces--;
            this.printBoard();
        }

        else{
            System.out.println("Please choose a different spot");
        }
        this.winCondition();
    }

    public void playGame(){
        Scanner scan = new Scanner(System.in);
        int counter = 0;
        while(!this.winCondition() && this.emptySpaces > 0){
            System.out.println("Please enter row: ");
            int row = scan.nextInt();
            System.out.println("Please enter col: ");
            int col = scan.nextInt();
            while(!this.canPutThere(row, col)){
                System.out.println("Please choose a different spot");
                this.printBoard();
                System.out.println("Please enter row: ");
                row = scan.nextInt();
                System.out.println("Please enter col: ");
                col = scan.nextInt();
            }
            if (counter % 2 == 0){
                this.set_O(row, col);
            }
            else{
                this.set_X(row, col);
            }
            counter++;
        }
    }
}
