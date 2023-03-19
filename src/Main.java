import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
public class Main {
    public static int counter=0;
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Board board = new Board();


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
                    board.print(rows, columns);
                    Instant start = Instant.now();
                    while (!board.finishGame()) {
                        board.showMenuInTurn(actualTurn);
                        selectionOptionInmenuInTurn = reader.nextInt();
                        if (selectionOptionInmenuInTurn == 1) {
                            int dice = (int) (Math.random() * 6 + 1);
                            board.rollDice(dice, actualTurn);
                            board.print(rows, columns);
                            actualTurn++;
                            if (actualTurn == 4) {
                                actualTurn = 1;
                            }
                        } else {
                            board.generateSnakes(snakes, boardSize);
                        }
                    }
                    Instant end = Instant.now();
                    Duration interval = Duration.between(start, end);
                    int time = (int) interval.getSeconds();
                    board.addScore(time);
                    actualTurn=1;
                    board.leaderBoard();
                    System.out.println("\nReiniciando juego...");
                    board.cleanBoard();
                }
        } while (option!=2);
    }

}