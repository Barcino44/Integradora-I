import java.lang.Math;
public class Board {
    private Node head;
    private Node tail;

    private Node snake;
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

    public void generateSnakeAndLadderBoard(Node current){
        if(current==snake){
            System.out.println();
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

