public class Player {
    private String name;
    private char token; // Player's token ('X' or 'O')

    public Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public char getToken() {
        return token;
    }
}
