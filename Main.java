/***
 * Author: Hugo Augusto Roque Paulista
 * BSI18
 * Programação Orientada a Objetos 2
 * 29/08/2019
 * TIC TAC TOE GAME
 */

import java.util.Scanner;
class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        Player player1 = new Player();
        Player player2 = new Player();
        Board board = new Board();
        
        int proceed  = 1;
        int current = 0;
        
        System.out.println("Welcome to Tic-tac-toe game. Please insert your name:\nPlayer 1: ");
        player1.setName(scan.nextLine());

        System.out.println("Player 2: ");
        player2.setName(scan.nextLine());

        System.out.println("This is the game board format: ");
        board.ShowBoard();

        System.out.println("\n" + player1.getName() + ", please choose what character will represent you\n X or O available");
        player1.setChosen(player1.ValidateChar());

        boolean valid = false;
       
        while (!valid) {
            System.out.println("\n" + player2.getName() +", please choose what character will represent you\n X or O available");
            char c = player2.ValidateChar();
            if (c != player1.getChoosen()) {
                player2.setChosen(c);
                valid = true;
            } else {
                System.out.println("The character must be different from Player 1");
                c = player2.ValidateChar();
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
                        scan.close();
                        return ;
                    }
                    if (win == player2.getChoosen()) {
                        System.out.println( player2.getName() + " WON");
                        scan.close();
                        return ;
                    }
                }
            }
            if (counter >= 9) {
                System.out.println("Game Over, nobody won");
                scan.close();
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

}