import org.xml.sax.SAXNotRecognizedException;

import java.lang.Math;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Board {
    public static int counter = 0;
    private Node head; //Es la cabeza del tablero (casilla 1)
    private Node tail; //Es la cola del tablero (última casilla)
    public Node actualNode; // Me guarda el nodo actual.
    public static boolean isEmptySnakeHead = false; //Me verifica si la cabeza de la serpiente esta vacia para poder añadir una alli.
    public static boolean isEmptySnakeTail = false; //Me verifica si la cola de la serpiente esta vacia para poder añadir una alli.
    public static char i = 'A'; //Identificador de la serpiente
    public static int snakeTail = 0; //Me da la posicion de la cola de la serpiente (interactua con random)
    public static int snakeHead = 0; //Me da la posicion de la cola de la serpiente (interactua con random)
    public static boolean isEmptyLadderHead =false;
    public static boolean isEmptyLadderTail=false;
    public static int ladderId=0;
    public static int ladderTail=1;
    public static  int ladderHead=0;

    //Es mejor tener los jugadores en board para que no tengamos que acceder a algún nodo si queremos hacer operacion con ellos.
    Player playerOne = new Player(1, "$", 0);
    Player playerTwo = new Player(2, "%", 0);
    Player playerThree = new Player(3, "#", 0);
    Random random = new Random();

    public void generateBoard(int i, int boardSize) {
        /*Generamos los tres jugadores, con sus parametros iniciales
         * ni su turno ni su icono serán modificados, pues se encuentran preestablecidos*/
        head = new Node(i, playerOne, playerTwo, playerThree);
        tail = head;
        generateBoardRecursive(i + 1, boardSize);
    }

    private void generateBoardRecursive(int i, int boardSize) {
        if (boardSize < i) {
            return;
        }
        Node emptyNode = new Node(i, null, null, null);
        tail.setNext(emptyNode); //La posicion despues de la cola es el nodo entrante. (creo el enlace ->)
        emptyNode.setPrevious(tail); //La posicion previa del nodo entrante es la cola. (creo el enlace <-)
        tail = emptyNode;//Tail es ahora el nodo entrante.
        generateBoardRecursive(i + 1, boardSize);
    }

    public void verifyEmptySpaceHeadSnake(Node current) {
        if (current.getSnake() == null &&current.getLadder()==null && snakeHead == current.getNumber()) {
            isEmptySnakeHead = true;
            return;
        } else if ((current.getSnake() != null || current.getLadder()!=null) && snakeHead == current.getNumber()) {
            isEmptySnakeHead = false;
            return;
        }
        verifyEmptySpaceHeadSnake(current.getNext());
    }

    private void verifyEmptySpaceTailSnake(Node current){
            if (current.getLadder() == null && current.getSnake()==null&& snakeTail == current.getNumber()) {
                isEmptySnakeTail = true;
                return;
            }
            else if((current.getSnake() != null||current.getLadder()!=null)&&snakeTail==current.getNumber()){
                isEmptySnakeTail =false;
                return;
            }
            verifyEmptySpaceTailSnake(current.getNext());
        }
    public void generateSnakes(int numberOfSnakes, int boardSize) {
        if(numberOfSnakes==0) {
            ladderId=1;
            return;
        }
        randomHeadSnake(boardSize);
        randomTailSnake(boardSize);
        verifyEmptySpaceHeadSnake(head);
        verifyEmptySpaceTailSnake(head);
            if (isEmptySnakeHead && isEmptySnakeTail) {
                generateSnakes(head, numberOfSnakes, boardSize);
            } else {
                generateSnakes(numberOfSnakes, boardSize);
            }
    }
    private void generateSnakes(Node current, int numberOfSnakes, int boardSize) {
        if (current == null) {
            i++;
            generateSnakes(numberOfSnakes-1,boardSize);
        }
        else {
            char ser = i;
                if (current.getNumber() == snakeTail) {
                    Snake snake = new Snake(' ', ser);
                    current.setSnake(snake);
                }
                else if(current.getNumber() == snakeHead) {
                    Snake snake = new Snake(ser, ' ');
                    current.setSnake(snake);
                }
                generateSnakes(current.getNext(), numberOfSnakes, boardSize);
            }
    }
    private void randomHeadSnake(int boardSize){
        snakeHead=random.nextInt(boardSize / 2, boardSize - 1);
    }
    private void randomTailSnake(int boardSize) {
        snakeTail = random.nextInt(2, boardSize / 2);
    }
    public void verifyEmptySpaceHeadLadder(Node current) {
        if (current.getLadder() == null && current.getSnake()==null && ladderHead == current.getNumber()) {
            isEmptyLadderHead = true;
            return;
        } else if ((current.getSnake() != null || current.getLadder()!=null) && ladderHead==current.getNumber()) {
            isEmptyLadderHead = false;
            return;
        }
        verifyEmptySpaceHeadLadder(current.getNext());
    }
    private void verifyEmptySpaceTailLadder(Node current){
        if (current.getSnake() == null && current.getLadder()==null && ladderTail == current.getNumber()) {
            isEmptyLadderTail = true;
            return;
        } else if ((current.getSnake() != null || current.getLadder()!=null) && ladderTail==current.getNumber()) {
            isEmptyLadderTail = false;
            return;
        }
        verifyEmptySpaceTailLadder(current.getNext());
    }
    public void generateLadders(int numberOfLadders, int boardSize) {
        if(numberOfLadders==0) {
            ladderId=0;
            return;
        }
        randomHeadLadder(boardSize);
        randomTailLadder(boardSize);
        verifyEmptySpaceTailLadder(head);
        verifyEmptySpaceHeadLadder(head);
        if (isEmptyLadderHead && isEmptyLadderTail) {
            generateLadders(head, numberOfLadders, boardSize);
        } else {
            generateLadders(numberOfLadders, boardSize);
        }
    }
    private void generateLadders(Node current, int numberOfSnakes, int boardSize) {
        if (current == null) {
            ladderId++;
            generateLadders(numberOfSnakes-1,boardSize);
        }
        else {
            int escalera = ladderId;
            if (current.getNumber() == ladderTail) {
                Ladder ladder = new Ladder(0, escalera);
                current.setLadder(ladder);
            }
            else if(current.getNumber() == ladderHead) {
                Ladder ladder = new Ladder(escalera, 0);
                current.setLadder(ladder);
            }
            generateLadders(current.getNext(), numberOfSnakes, boardSize);
        }
    }

    private void randomHeadLadder(int boardSize) {
        ladderHead = random.nextInt(boardSize / 2, boardSize - 1);
    }
    private void randomTailLadder(int boardSize) {
        ladderTail = random.nextInt(2, boardSize / 2);
    }
    public void printSnakesNLaddersBoard(int rows, int columns) {
        printLaddersNSnakes(tail, rows, columns);
    }
    /*
    Imprime una fila de forma ascendente
     */
    private void printLaddersNSnakes(Node current, int rows, int columns) {
        if (current == null) {
            return;
        }
        if (rows % 2 == 0) { //Significa que el número de filas es par. Como imprimo desde la cola, para que la primera casilla del tablero esté en la esquina inferior izq, debo primero imprimir en orden.
            if (counter != columns) { //Si el contador es != a las columnas, imprimimos de forma normal.
                counter++;
                System.out.print(current.printLaddersNSnakesStatus() + " ");
                printLaddersNSnakes(current.getPrevious(), rows, columns);
            } else {  //Si no, hacemos salto de línea, seteamos al contador y vamos al otro método.
                System.out.println();
                counter = 0;
                printReverseLaddersNSnakes(current, rows, columns);
                continuePrintingLaddersNSnakePairRows(rows, columns);
            }
        }  else { //Significa que el número de filas es impar. Como imprimo desde la cola, para que el primer nodo quede en la esquina inferior izq debo comenzar imprimiendo en reversa.
            if (counter < columns) {
                printReverseLaddersNSnakes(current, rows, columns); //Cuando imprimo en reversa <- , llego a la ultima casilla.
                continuePrintingLaddersNSnakeNoPairRows(rows, columns);//Este método me guarda esa casilla y vuelve a llamar a este método para que me imprima para el otro lado ->.
            }
            else{
                if(counter>columns*2-1){
                    counter=0;
                    System.out.println();
                    printLaddersNSnakes(current,rows,columns);
                }
                else {
                    counter++;
                    System.out.print(current.printLaddersNSnakesStatus() + " ");
                    printLaddersNSnakes(current.getPrevious(), rows, columns);
                }
            }
        }
    }
    /*
    Imprime una fila en reversa.
     */
    private void printReverseLaddersNSnakes(Node current, int rows, int columns) {
        if (columns == counter || current == null) { //Si las columnas son iguales al contador o el nodo actual, es nulo, finaliza el llamado.
            counter=0;
            actualNode = current; //Antes de retornar, guardo el nodo actual en el que me encuentro.
            return;
        }
        counter++;
        printReverseLaddersNSnakes(current.getPrevious(), rows, columns); //Hago recursion hasta que se cumplan los casos bases.
        System.out.print(current.printLaddersNSnakesStatus() + " "); //Cuando hago return, me imprime de atrás para adelante.
    }
    public void continuePrintingLaddersNSnakePairRows(int rows, int columns) {
        if (actualNode != null) {
            System.out.println();
            printLaddersNSnakes(actualNode, rows, columns); //Vuelvo a llamar a print row para que me continue con la impresión.
        }
    }
    public void continuePrintingLaddersNSnakeNoPairRows(int rows, int columns) {
        if (actualNode != null) {
            counter=columns;
            System.out.println();
            printLaddersNSnakes(actualNode, rows, columns); //Vuelvo a llamar a print row para que me continue con la impresión.
        }
    }

    public void showMenuInTurn(int actualTurn) {

        showMenuInTurn(head, actualTurn);

    }

    private void showMenuInTurn(Node current, int actualTurn) {

        if (current == null) {
            return;
        }

        String msg = "1.Tirar dado\n2.Ver escaleras y serpientes";

        if (current.getPlayerOne() != null && current.getPlayerOne().getTurn() == actualTurn) {

            System.out.println("\nJugador " + playerOne.getIcon() + " es tu turno\n" + msg);
        } else if (current.getPlayerTwo() != null && current.getPlayerTwo().getTurn() == actualTurn) {

            System.out.println("\nJugador " + playerTwo.getIcon() + " es tu turno\n" + msg);

        } else if (current.getPlayerThree() != null && current.getPlayerThree().getTurn() == actualTurn) {

            System.out.println("\nJugador " + playerThree.getIcon() + " es tu turno\n" + msg);

        }
        showMenuInTurn(current.getNext(), actualTurn);
    }

    public void rollDice(int dice, int actualTurn) {

        rollDice(head, dice, actualTurn);
    }
    /*
    Tirar dado.
     */
    private void rollDice(Node current, int dice, int actualTurn) {

        if (dice == 0) { //El caso base es que el dado sea ==0.
            return;
        }
        if (current.getNext() == null) {
            return;
        }
        if (actualTurn == 1) { //El jugador actual es el 1.

            if (current.getPlayerOne() != null) {
                Player thePlayer = current.getPlayerOne(); //Obtengo el jugador del nodo.
                current.setPlayerOne(null); //Seteo el nodo actual con nulo.
                current.getNext().setPlayerOne(thePlayer); //Seteo el nodo siguiente con el jugador.
                rollDice(current.getNext(), dice - 1, actualTurn); //Hago recursion y le quito 1 al dado, ya que me moví.
            } else {
                rollDice(current.getNext(), dice, actualTurn); //Si no, hago recursion hasta que encuentre al jugador.
            }
                                                               //Es igual con los demás players.
        } else if (actualTurn == 2) { //El jugador actual es el 2.
            if (current.getPlayerTwo() != null) {
                Player thePlayer = current.getPlayerTwo();
                current.setPlayerTwo(null);
                current.getNext().setPlayerTwo(thePlayer);
                rollDice(current.getNext(), dice - 1, actualTurn);
            } else {
                rollDice(current.getNext(), dice, actualTurn);
            }
        } else { //El jugador actual es el 3.
            if (current.getPlayerThree() != null) {
                Player thePlayer = current.getPlayerThree();
                current.setPlayerThree(null);
                current.getNext().setPlayerThree(thePlayer);
                rollDice(current.getNext(), dice - 1, actualTurn);
            } else {
                rollDice(current.getNext(), dice, actualTurn);
            }
        }
    }

    public void verifySnake(){
        verifySnake(head);
    }
    private void verifySnake(Node current) {
        if(current==null){
            return;
        }
        if (current.getSnake() != null && current.getSnake().getHead() != ' ') {
            char headSnake = current.getSnake().getHead();
            if (current.getPlayerOne() != null) {
                Player thePlayer = playerOne;
                current.setPlayerOne(null);
                goToBackWard(current,headSnake,thePlayer);
            } else if (current.getPlayerTwo() != null) {
                Player thePlayer = playerTwo;
                current.setPlayerTwo(null);
                goToBackWard(current,headSnake,thePlayer);
            } else if (current.getPlayerThree() != null) {
                Player thePlayer = playerThree;
                current.setPlayerThree(null);
                goToBackWard(current,headSnake,thePlayer);
            }
        }
        verifySnake(current.getNext());
    }

    public void goToBackWard(Node current, char snakeHead, Player player) {
        if (current.getSnake()!=null) {
            if (current.getSnake().getTail()==snakeHead) {
                if(player==playerOne){
                    current.setPlayerOne(playerOne);
                    return;
                }
                else if(player==playerTwo){
                    current.setPlayerTwo(playerTwo);
                    return;
                }
                else if(player==playerThree){
                    current.setPlayerThree(playerThree);
                    return;
                }
            }
        }
        goToBackWard(current.getPrevious(),snakeHead,player);
    }
    public void verifyLadder(){
        verifyLadder(head);
    }
    private void verifyLadder(Node current) {
        if(current==null){
            return;
        }
        if (current.getLadder() != null && current.getLadder().getTail() != 0) {
            int tailLadder = current.getLadder().getTail();
            if (current.getPlayerOne() != null) {
                Player thePlayer = playerOne;
                current.setPlayerOne(null);
                goToForward(current,tailLadder,thePlayer);
            } else if (current.getPlayerTwo() != null) {
                Player thePlayer = playerTwo;
                current.setPlayerTwo(null);
                goToForward(current,tailLadder,thePlayer);
            } else if (current.getPlayerThree() != null) {
                Player thePlayer = playerThree;
                current.setPlayerThree(null);
                goToForward(current,tailLadder,thePlayer);
            }
        }
        verifyLadder(current.getNext());
    }

    public void goToForward(Node current, int tailLadder, Player player) {
        if (current.getLadder()!=null) {
            if (current.getLadder().getHead()==tailLadder) {
                if(player==playerOne){
                    current.setPlayerOne(playerOne);
                    return;
                }
                else if(player==playerTwo){
                    current.setPlayerTwo(playerTwo);
                    return;
                }
                else if(player==playerThree){
                    current.setPlayerThree(playerThree);
                    return;
                }
            }
        }
        goToForward(current.getNext(),tailLadder,player);
    }
    /*
    Activador de impresión.
     */
    public void print(int rows, int columns) {
        printRow(tail, rows, columns);
    }
    /*
    Imprime una fila de forma ascendente
     */
    private void printRow(Node current, int rows, int columns) {
        if (current == null) {
            return;
        }
        if (rows % 2 == 0) { //Significa que el número de filas es par. Como imprimo desde la cola, para que la primera casilla del tablero esté en la esquina inferior izq, debo primero imprimir en orden.
            if (counter != columns) { //Si el contador es != a las columnas, imprimimos de forma normal.
                counter++;
                System.out.print(current.printStatus() + " ");
                printRow(current.getPrevious(), rows, columns);
            } else {  //Si no, hacemos salto de línea, seteamos al contador y vamos al otro método.
                System.out.println();
                counter = 0;
                printReverseRow(current, rows, columns);
                continuePrintingPairRows(rows, columns);
            }
        } else { //Significa que el número de filas es impar. Como imprimo desde la cola, para que el primer nodo quede en la esquina inferior izq debo comenzar imprimiendo en reversa.
            if (counter < columns) {
                printReverseRow(current, rows, columns); //Cuando imprimo en reversa <- , llego a la ultima casilla.
                continuePrintingNoPairRows(rows, columns);//Este método me guarda esa casilla y vuelve a llamar a este método para que me imprima para el otro lado ->.
            }
            else{
                if(counter>columns*2-1){
                    counter=0;
                    System.out.println();
                    printRow(current,rows,columns);
                }
                else {
                    counter++;
                    System.out.print(current.printStatus() + " ");
                    printRow(current.getPrevious(), rows, columns);
                }
            }
        }
    }
    /*
    Imprime una fila en reversa.
     */
    private void printReverseRow(Node current, int rows, int columns) {
        if (columns == counter || current == null) { //Si las columnas son iguales al contador o el nodo actual, es nulo, finaliza el llamado.
            counter = 0;
            actualNode = current; //Antes de retornar, guardo el nodo actual en el que me encuentro.
            return;
        }
        counter++;
        printReverseRow(current.getPrevious(), rows, columns); //Hago recursion hasta que se cumplan los casos bases.
        System.out.print(current.printStatus() + " "); //Cuando hago return, me imprime de atrás para adelante.
    }
    /*
    Este método me permite seguir imprimiendo desde el nodo actual obtenido en print reverse.
     */
    public void continuePrintingPairRows(int rows, int columns) {
        if (actualNode != null) {
            System.out.println();
            printRow(actualNode, rows, columns); //Vuelvo a llamar a print row para que me continue con la impresión.
        }
    }
    public void continuePrintingNoPairRows(int rows, int columns) {
        if (actualNode != null) {
            counter=columns;
            System.out.println();
            printRow(actualNode, rows, columns); //Vuelvo a llamar a print row para que me continue con la impresión.
        }
    }
    /*
    Método que me verifica si existe un jugador en la cola
     */
    public boolean finishGame() {
        boolean isFinished = false;
        if (tail.getPlayerOne() != null || tail.getPlayerTwo() != null || tail.getPlayerThree() != null) {
            isFinished = true;
        }
        return isFinished;
    }
    /*
    Activador de clean board.
     */
    public void cleanBoard() {
        cleanBoard(head);
    }
    /*
     Método que me limpia el tablero.
     */
    public void cleanBoard(Node current) {
        boolean isFinished = finishGame();
        if (current == null) {
            return;
        }
        if (isFinished) {
            if (!current.printStatusWithoutNumber().equals("")) { //El metodo print status without number, me verifica si existe alguien en el nodo actual.
                current.setPlayerOne(null); //Si existe es porque el estado del nodo sin el número es distinto a "", entonces hago null.
                current.setPlayerTwo(null);
                current.setPlayerThree(null);
                head.setPlayerOne(playerOne); //Seteo los players en la cabeza
                head.setPlayerTwo(playerTwo);
                head.setPlayerThree(playerThree);
            }
            cleanBoard(current.getNext()); //Realizo un recorrido total por el tablero
        }
    }
    /*
   Anado nodos al arbol binario de busqueda.
    */
    public void addScore(double time) { //Seteo el puntaje del jugador y añado el puntaje al árbol.
        double score = (600 - time) / 6;
        if (tail.getPlayerOne() != null) {
            playerOne.setScore(score);
            NodeScore newNodeScore = new NodeScore(playerOne.getScore(), playerOne);
            if (BST.getRoot() == null) {
                BST.setRoot(newNodeScore);
            } else {
                BST.addNode(BST.getRoot(), newNodeScore);
            }
        } else if (tail.getPlayerTwo() != null) {
            playerTwo.setScore(score);
            NodeScore newNodeScore = new NodeScore(playerTwo.getScore(), playerTwo);
            if (BST.getRoot() == null) {
                BST.setRoot(newNodeScore);
            } else {
                BST.addNode(BST.getRoot(), newNodeScore);
            }
        } else if (tail.getPlayerThree() != null) {
            playerThree.setScore(score);
            NodeScore newNodeScore = new NodeScore(playerThree.getScore(), playerThree);
            if (BST.getRoot()== null) {
                BST.setRoot(newNodeScore);
            } else {
                BST.addNode(BST.getRoot(), newNodeScore);
            }
        }
    }
    public void getLeaderBoard(){
        BST.leaderBoard();

    }
}


