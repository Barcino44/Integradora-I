import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Board {
    private Node head;
    private Node tail;

    private List<List<Integer>> serpientes = new ArrayList<>();
    Random random = new Random();

    public void generateBoard(int boardSize){
        /*Generamos los tres jugadores, con sus parametros iniciales
        * ni su turno ni su icono serán modificados, pues se encuentran preestablecidos*/
        Player playerOne = new Player(1,"$",0);
        Player playerTwo =new Player(2,"%",0);
        Player playerThree =new Player(3,"#",0);
        head = new Node(1, playerOne,playerTwo,playerThree);

        Node aux = head;
        for(int i=1; i<=boardSize;i++){
            Node nodeEmpty = new Node(i, null,null,null);
            aux.setNext(nodeEmpty);
            aux = aux.getNext();
        }
        tail = aux;
    }
    public void generateSnakes(int numberOfSnakes, int boardSize){

        generateSnakes(head, numberOfSnakes, boardSize);

    }

    private void generateSnakes(Node current,int numberOfSnakes,int boardSize) {

        /*int positionSnakeH = (int) (Math.floor(Math.random() * (2 - boardSize) + boardSize));

        if (positionSnakeH == current.getNumber()) {
            current.setSnake(current);
        }Lina : documenté esto porque no se que hiciste*/

       /* for (int i = 0; i < numberOfSnakes; i++) {

            char caracter = (char) ('A' + i); //Para asignarle el identificador a las serpientes
            int headS = random.nextInt(boardSize - 10
            ) + 1;
            int tailS = random.nextInt(boardSize- headS) + headS + 1;



        }*/

    }

//    public void generateSnakeAndLadderBoard(Node current){
//        if(current==){
//            System.out.println();
//        }
//    }
    public void showMenuInTurn(int actualTurn){

        showMenuInTurn(head,actualTurn);

    }
    private void showMenuInTurn(Node current, int actualTurn) {

        if(current==null){
            return;
        }

        String msg="1.Tirar dado\n2.Ver escaleras y serpientes";

        if (current.getPlayerOne()!=null && current.getPlayerOne().getTurn()==actualTurn) {

            System.out.println("\nJugador "+ current.getPlayerOne().getIcon() + " es tu turno\n" + msg);
        }
        else if (current.getPlayerTwo()!=null && current.getPlayerTwo().getTurn() == actualTurn) {

            System.out.println("\nJugador " + current.getPlayerTwo().getIcon() + " es tu turno\n"+ msg);

        }
        else if (current.getPlayerThree()!=null&&current.getPlayerThree().getTurn() == actualTurn){

            System.out.println("\nJugador" + current.getPlayerThree().getIcon() + " es tu turno\n" + msg);

        }
        showMenuInTurn(current.getNext(),actualTurn);
    }
    public void rollDice(int dice, int actualTurn){

        rollDice(head,dice,actualTurn);

    }
    private void rollDice(Node current,int dice,int actualTurn){

        if(dice==0){
            return;
        }
        if(actualTurn==1){

            if(current.getPlayerOne()!=null) {

                Player thePlayer = current.getPlayerOne();
                current.setPlayerOne(null);
                current.getNext().setPlayerOne(thePlayer);
                rollDice(current.getNext(), dice - 1,actualTurn);
            }
            else{
            rollDice(current.getNext(),dice,actualTurn);
            }
        }
        else if (actualTurn==2) {
            if(current.getPlayerTwo()!=null) {
                Player thePlayer = current.getPlayerTwo();
                current.setPlayerTwo(null);
                current.getNext().setPlayerTwo(thePlayer);
                rollDice(current.getNext(), dice - 1,actualTurn);
            }
            else{
                rollDice(current.getNext(),dice,actualTurn);
            }
        }
        else{
            if(current.getPlayerThree()!=null) {
                Player thePlayer = current.getPlayerThree();
                current.setPlayerThree(null);
                current.getNext().setPlayerThree(thePlayer);
                rollDice(current.getNext(), dice - 1,actualTurn);
            }
            else{
                rollDice(current.getNext(),dice,actualTurn);
            }
        }
    }
    public void print(){
        print(head);
    }
    private void print(Node current){

        if(current==null){
            return;
        }
        if(current.getPlayerOne()!=null&&current.getPlayerTwo()!=null&&current.getPlayerThree()!=null) {
            System.out.print("[" + current.getNumber() + current.getPlayerOne().getIcon() + current.getPlayerTwo().getIcon() + current.getPlayerThree().getIcon() + "]" + " ");
        }
        else if(current.getPlayerOne()!=null&&current.getPlayerTwo()!=null){
            System.out.print("["+current.getNumber()+current.getPlayerOne().getIcon()+current.getPlayerTwo().getIcon()+"]" + " ");
        }
        else if(current.getPlayerOne()!=null&&current.getPlayerThree()!=null){
            System.out.print("["+current.getNumber()+current.getPlayerOne().getIcon()+current.getPlayerThree().getIcon()+"]" + " ");
        }
        else if (current.getPlayerTwo()!=null&&current.getPlayerThree()!=null) {
            System.out.print("["+current.getNumber()+current.getPlayerTwo().getIcon()+current.getPlayerThree().getIcon()+"]" + " ");
        }
        else if(current.getPlayerOne()!=null){
            System.out.print("[" + current.getNumber()+current.getPlayerOne().getIcon() + "]" + " ");
        }
        else if(current.getPlayerTwo()!=null){
            System.out.print("[" + current.getNumber()+current.getPlayerTwo().getIcon() + "]" + " ");
        }
        else if(current.getPlayerThree()!=null){
            System.out.print("[" + current.getNumber()+current.getPlayerThree().getIcon() + "]" + " ");
        }
        else{
            System.out.print("[" + current.getNumber() + "]" + " ");
        }
        print(current.getNext());
    }
}

