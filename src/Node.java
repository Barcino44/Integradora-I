public class Node {
    private int number;
    private Player playerOne;

    private Player playerTwo;

    private Player playerThree;
    private Node snake;
    private Node ladder;
    private Node next;
    private Node previous;

    public Node(int number, Player playerOne, Player playerTwo, Player playerThree) {
        this.number = number;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
    }

    public Node getSnake() {
        return snake;
    }

    public void setSnake(Node snake) {
        this.snake = snake;
    }

    public Node getLadder() {
        return ladder;
    }

    public void setLadder(Node ladder) {
        this.ladder = ladder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayerThree() {
        return playerThree;
    }

    public void setPlayerThree(Player playerThree) {
        this.playerThree = playerThree;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
