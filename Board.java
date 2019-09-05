
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
                return previous.charAt(0);
            }
            if (countO == 3 ) {
                return previous.charAt(0);
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
                return previous.charAt(0);
            }
            if (countO == 3 ) {
                return previous.charAt(0);
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
            int countX = this.CountOccurrencesOf(previous.toUpperCase(), "X");
            int countO = this.CountOccurrencesOf(previous.toUpperCase(), "O");
            if (countX == 3) {
                System.out.println(countX);
                return previous.charAt(0);
            }
            if (countO == 3 ) {
                return previous.charAt(0);
            }
        }
        /** diagonal secundaria */
        previous = "";
        for (int i = 0; i < 7; i = i + 2) {
            previous += positions[i];

            int countX = this.CountOccurrencesOf(previous.toLowerCase(), "x");
            int countO = this.CountOccurrencesOf(previous.toLowerCase(), "o");

            if (countX == 3) {
                return previous.charAt(0);
            }
            if (countO == 3 ) {
                return previous.charAt(0);
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