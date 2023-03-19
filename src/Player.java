public class Player {
    private int turn;
    private String icon;

    private double  score;

    public Player(int turn, String icon, double score) {
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
