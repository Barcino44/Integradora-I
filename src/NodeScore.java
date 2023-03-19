public class NodeScore {
    private double score;
    private Player player;
    private NodeScore right;
    private NodeScore left;

    public NodeScore(double score, Player player) {
        this.score= score;
        this.player = player;
    }
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NodeScore getRight() {
        return right;
    }

    public void setRight(NodeScore right) {
        this.right = right;
    }

    public NodeScore getLeft() {
        return left;
    }

    public void setLeft(NodeScore left) {
        this.left = left;
    }
}
