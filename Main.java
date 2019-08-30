/***
 * Author: Hugo Augusto Roque Paulista
 * BSI18
 * Programação Orientada a Objetos 2
 * 29/08/2019
 * TIC TAC TOE GAME
 */
import java.util.Scanner;

class Player {
    private String name;
    private char choosenCharacter; 
    Player() {
        this.init();
    }
    private void init() {
        this.name = "Guest";
        this.choosenCharacter = 'u';
    }

    public String getName() {
        return this.name;
    }
    
    public char getChoosen() {
        return this.choosenCharacter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoosen(char choosen) {
        this.choosenCharacter = choosen;
    }
}

class Board {
    private char[] positions;
    Board() {
        this.init();
    }

    private void init() {
        this.positions = new char[9];
        for (int i = 0; i < 9; i++) {
            this.positions[i] = 'f';
        }
    }

    public void setPosition(int position, char content) {
        this.positions[position] = content;
    }

    public char getPosition(int position) {
        return this.positions[position];
    }

    public void setMatrix(char[] content) {
        for(int i = 0; i < content.length; i++) {
            this.setPosition(i, content[i]);
        }
    } 

    public char[] getMatrix() {
        return this.positions;
    }

    public void ShowBoard() {
        for (int i = 0; i < this.positions.length; i++) {
            if (i % 3 == 0 && i > 0) {
                System.out.printf("\n");
            }
            if (this.positions[i] == 'f') {
                System.out.printf("%d ", i + 1);
            } else {
                System.out.printf("%c ", this.positions[i]);
            }

        }

        System.out.printf("\n");
    }
    private int CountOccurrencesOf(String str, String ch) {
        int counter = 0;

        for (int i = 0; i < str.length(); i++ ) {
            if (str.toCharArray()[i] == ch.charAt(0) ) {
                counter++;
            }
        }

        return counter;
    }
    private char VerifyLines() {
        String previous;
        for (int i= 0; i <= 6; i = i + 3) {
            previous = "";
            for (int j = 0; j < 3; j++) {
                previous += positions[j + i];
            }
            int countX = this.CountOccurrencesOf(previous.toLowerCase(), "x");
            int countO = this.CountOccurrencesOf(previous.toLowerCase(), "o");

            if (countX == 3) {
                return 'x';
            }
            if (countO == 3 ) {
                return 'o';
            }

        }

        return 'f';
    }

    private char VerifyColumns() {

        String previous;

        for (int j = 0; j < 3; j++) {
            previous = "";
            for (int i= 0; i <= 6; i = i + 3) {
                previous += positions[j + i];
            }
            int countX = this.CountOccurrencesOf(previous.toLowerCase(), "x");
            int countO = this.CountOccurrencesOf(previous.toLowerCase(), "o");

            if (countX == 3) {
                return 'x';
            }
            if (countO == 3 ) {
                return 'o';
            }

        }

        return 'f';
    }

    private char VerifyDiagonals() {

        String previous;
        previous = "";
    /**diagonal principal */
        for (int i = 0; i < 9; i = i + 4) {
            previous += positions[i];

            int countX = this.CountOccurrencesOf(previous.toLowerCase(), "x");
            int countO = this.CountOccurrencesOf(previous.toLowerCase(), "o");

            if (countX == 3) {
                return 'x';
            }
            if (countO == 3 ) {
                return 'o';
            }
        }
        /** diagonal secundaria */
        previous = "";
        for (int i = 0; i < 7; i = i + 2) {
            previous += positions[i];

            int countX = this.CountOccurrencesOf(previous.toLowerCase(), "x");
            int countO = this.CountOccurrencesOf(previous.toLowerCase(), "o");

            if (countX == 3) {
                return 'x';
            }
            if (countO == 3 ) {
                return 'o';
            }
        }

        return 'f';
    }

    public char VerifyWinner() {
        char lines = this.VerifyLines();
        if (lines != 'f') {
            return lines;
        }
        char columns = this.VerifyColumns();
        if (columns != 'f') {
            return columns;
        }

        char diagonals = this.VerifyDiagonals();
        if (diagonals != 'f') {
            return diagonals;
        }

        return 'f';
    }
}

class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();
        Board board = new Board();
        int proceed  = 1;
        int current = 0;
        Main main = new Main();
        
        System.out.println("Welcome to Tic-tac-toe game. Please insert your name:\nPlayer 1: ");
        player1.setName(scan.nextLine());

        System.out.println("Player 2: ");
        player2.setName(scan.nextLine());

        System.out.println("This is the game board format: ");
        board.ShowBoard();
        System.out.println("\n" + player1.getName() + ", please choose what character will represent you\n X or O available");
        
        player1.setChoosen(main.ValidateChar());

        boolean valid = false;
       
        while (!valid) {
            System.out.println("\n" + player2.getName() +", please choose what character will represent you\n X or O available");
            char c = main.ValidateChar();
            if (c != player1.getChoosen()) {
                player2.setChoosen(c);
                valid = true;
            } else {
                System.out.println("The character must be different from Player 1");
                c = main.ValidateChar();
            }
        }

        int counter = 0;
        while (proceed != 0) {

            board.ShowBoard();
            if (counter >= 4 ) {
                char win = board.VerifyWinner();

                if (win != 'f') {
                    if (win == player1.getChoosen()) {
                        System.out.println( player1.getName() + " WON");
                        return ;
                    }
                    if (win == player2.getChoosen()) {
                        System.out.println( player2.getName() + " WON");

                        return ;
                    }
                }
            }
            if (counter >= 9) {
                System.out.println("Game Over, nobody won");
                return ;
            }
            if (current == 0 ) {
                System.out.println( player1.getName() + ", please select the board position");
                int pos = scan.nextInt();
                if (board.getPosition(pos - 1) == 'f') {
                    board.setPosition( pos - 1, player1.getChoosen());
                    current = 1;
                    counter++;
                } else {
                    System.out.println("Please select another position");
                }
                
            } else if (current == 1) {
                System.out.println( player2.getName() + ", please select the board position");
                int pos = scan.nextInt();
                if (board.getPosition(pos - 1) == 'f') {
                    board.setPosition( pos - 1, player2.getChoosen());
                    current = 0;
                    counter++;
                } else {
                    System.out.println("Please select another position");
                }
            }

        }
    }

    private char ValidateChar() {
        Scanner scanner = new Scanner(System.in);
        try {
            String str = scanner.nextLine();
            char c = str.charAt(0);
            System.out.println("Character selected: " + c);
            if (!str.equalsIgnoreCase("X") && !str.equalsIgnoreCase("O")) {
                scanner.close();
                throw new Exception("The inserted character is not a valid format");
            }
            return c;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 'f';
        }
    }

}


