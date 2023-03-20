public class Node {
    private int number;
    private Player playerOne, playerTwo, playerThree;
    private Snake snake;

    private Node next;
    private Node previous;
    private String letter;

    /*Con la ayuda de este constructor, establecemos los parametros del tablero inicial
    *number int: tamaño del tablero
    * Players: Jugadores que serán colocados en la primer casilla del tablero al iniciar el juego*/
    public Node( int number, Player playerOne, Player playerTwo, Player playerThree) {
        this.number = number;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

//    public Node getLadder() {
//        return ladder;
//    }
//
//    public void setLadder(Node ladder) {
//        this.ladder = ladder;
//    }

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

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
    /*
    Me imprime el estado el nodo (Es clave para el momento de la impresión)
     */
    public String printStatus(){
        String statusOne="";
        String statusTwo="";
        String statusThree="";
        if(playerOne!=null) {
            statusOne = playerOne.getIcon();
        }
        if (playerTwo!=null){
            statusTwo=playerTwo.getIcon();
        }
        if(playerThree!=null) {
            statusThree = playerThree.getIcon();
        }
        return "[" + number +
                statusOne+
                statusTwo+
                statusThree+
                "]";
    }
    /*
    Me imprime el estado sin el número (Es clave para limpiar el tablero)
     */
    public String printStatusWithoutNumber(){
        String statusOne="";
        String statusTwo="";
        String statusThree="";
        if(playerOne!=null) {
            statusOne = playerOne.getIcon();
        }
        if (playerTwo!=null){
            statusTwo=playerTwo.getIcon();
        }
        if(playerThree!=null) {
            statusThree = playerThree.getIcon();
        }
        return statusOne+ statusTwo+ statusThree;
    }
    public String printLaddersNSnakesStatus(){
        char snake=' ';
        if(this.snake!=null&&this.snake.getHead()!= ' '){
            snake=this.snake.getHead();
        }
        if(this.snake!=null&&this.snake.getTail()!= ' '){
            snake=this.getSnake().getTail();
        }
        return "["+snake+"]";
    }
}
