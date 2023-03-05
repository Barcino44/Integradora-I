import java.lang.Math;
public class Board {
    private Node head;
    private Node tail;

    public void generateBoard(int number){
        Player playerOne= new Player(1,"$",0);
        Player playerTwo=new Player(2,"%",0);
        Player playerThree=new Player(3,"#",0);
        Node nodeHead=new Node(number,playerOne,playerTwo,playerThree);
        Node nodeEmpty=new Node(number,null,null,null);
        if (head == null) { //Si la cabeza es vacia, le metemos el nodo con los jugadores
            head = nodeHead;
            tail=nodeHead;
        }else{ //Si no, dejamos los demas nodos sin jugadores.
            tail.setNext(nodeEmpty);
            nodeEmpty.setPrevious(tail);
            tail = nodeEmpty;
        }
    }
    public void generateSnakes(int numberOfSnakes, int boardSize){
        generateSnakes(head, numberOfSnakes, boardSize);
    }
    public void generateSnakes(Node current,int numberOfSnakes,int boardSize) {
        int positionSnakeH = (int) (Math.floor(Math.random() * (2 - boardSize) + boardSize));
        if (positionSnakeH == current.getNumber()) {
            current.setSnake(current);
        }
    }

//    public void generateSnakeAndLadderBoard(Node current){
//        if(current==){
//            System.out.println();
//        }
//    }
    public void showMenuInTurn(int actualTurn){
        showMenuInTurn(head,actualTurn);
    }
    public void showMenuInTurn(Node current, int actualTurn) {
        if(current==null){
            return;
        }
        String msg="1. Tirar dado\n" +
                "2.Ver escaleras y serpientes";
        if (current.getPlayerOne()!=null&&current.getPlayerOne().getTurn()==actualTurn) {
            System.out.println("\nJugador" + current.getPlayerOne().getIcon() + " es tu turno\n" +
                    msg);
        }
        else if (current.getPlayerTwo()!=null&&current.getPlayerTwo().getTurn() == actualTurn) {
            System.out.println("\nJugador " + current.getPlayerTwo().getIcon() + " es tu turno\n"+
                    msg);
        }
        else if (current.getPlayerThree()!=null&&current.getPlayerThree().getTurn() == actualTurn){
            System.out.println("\nJugador" + current.getPlayerThree().getIcon() + " es tu turno\n" +
                    msg);
        }
        showMenuInTurn(current.getNext(),actualTurn);
    }
    public void rollDice(int dice, int actualTurn){
        rollDice(head,dice,actualTurn);
    }
    public void rollDice(Node current,int dice,int actualTurn){
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

