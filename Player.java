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

    public void setChosen(char choosen) {
        this.choosenCharacter = choosen;
    }

    public char ValidateChar() {
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
