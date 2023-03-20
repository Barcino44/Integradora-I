import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Board board = new Board();
        
        menu();
    }
    
    public static void menu(){
        int actualTurn=1; //contador de turnos
        int snakes, ladders=0;
        int option, selectionOptionInmenuInTurn;
        int rows,columns,boardSize=0;

        do {
            System.out.println("Bienvenido a LaddersN'Snakes");
            System.out.println("1.Jugar");
            System.out.println("2.Salir");
            option = reader.nextInt();
            if(option==1) {
                System.out.println("Ingrese el numero de filas que desea anadir");
                    while (!reader.hasNextInt()) {
                        reader.next();
                        System.out.println("Invalido, ingrese un numero");
                    }
                    rows = reader.nextInt();
                    System.out.println("Ingrese el numero de columnas que desea anadir");
                    while (!reader.hasNextInt()) {
                        reader.next();
                        System.out.println("Invalido, ingrese un numero valido");
                    }
                    columns = reader.nextInt();
                    boardSize = columns * rows;
                    board.generateBoard(1,boardSize);
                    System.out.println("Ingrese el numero de serpientes");
                    while (!reader.hasNextInt()) {
                        reader.next();
                        System.out.println("Invalido, ingrese un numero valido");
                    }
                    snakes=reader.nextInt();
                    System.out.println("Ingrese el numero de escaleras");
                     while (!reader.hasNextInt()) {
                        reader.next();
                        System.out.println("Invalido, ingrese un numero valido");
                    }
                    ladders=reader.nextInt();
                    board.generateSnakes(snakes, boardSize);
                    board.generateLadders(ladders,boardSize);
                    board.print(rows, columns);
                    Instant start = Instant.now();
                    while (!board.finishGame()) {
                        board.showMenuInTurn(actualTurn);
                        selectionOptionInmenuInTurn = reader.nextInt();
                        if (selectionOptionInmenuInTurn == 1) {
                            int dice = (int) (Math.random() * 6 + 1);
                            board.rollDice(dice, actualTurn);
                            board.verifySnake();
                            board.verifyLadder();
                            board.print(rows, columns);
                            actualTurn++;
                            if (actualTurn == 4) {
                                actualTurn = 1;
                            }
                        } else if (selectionOptionInmenuInTurn==2){
                            board.printSnakesNLaddersBoard(rows,columns);
                        }
                    }
                    Instant end = Instant.now();
                    Duration interval = Duration.between(start, end);
                    int time = (int) interval.getSeconds();
                    board.addScore(time);
                    actualTurn=1;
                    System.out.println("\nFin de la partida\n");
                    System.out.print("Lista de puntajes:");
                    board.getLeaderBoard();
                    System.out.println();
                    System.out.println("\nReiniciando juego...\n");
                    board.cleanBoard();
                }
        } while (option!=2);
    }



}
