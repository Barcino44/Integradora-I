public class Player {
    private int turn;
    private String icon;

    private int score;

    public Player(int turn, String icon, int score) {
        this.turn = turn;
        this.icon = icon;
        this.score = score;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
