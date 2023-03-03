import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner reader = new Scanner(System.in);
        int rows=0;
        int columns=0;
        int boardSize=0;
        int snakes=0;
        int ladders=0;
        int option;
        do {
            System.out.println("\nWelcome to ladders and Snakes");
            System.out.println("1.Play");
            System.out.println("2.Exit");
            option = reader.nextInt();

            if(option==1) {
                System.out.println("Enter the number of rows that you want to add");
                while (!reader.hasNextInt()) {
                    reader.next();
                    System.out.println("Invalid, enter a level number");
                }
                rows = reader.nextInt();
                System.out.println("Enter the number of rows that you want to add");
                while (!reader.hasNextInt()) {
                    reader.next();
                    System.out.println("Invalid, enter a level number");
                }
                columns = reader.nextInt();
                boardSize=columns*rows;
                for (int i = 1; i <= boardSize; i++) {
                    board.generateBoard(i);
                }
                System.out.println("Ingrese el numero de serpientes");
                while (!reader.hasNextInt()) {
                    reader.next();
                    System.out.println("Invalid, enter a level number");
                }
                snakes=reader.nextInt();

                while (boardSize/7>=snakes){
                    System.out.println("You have reach the maximum of snakes");
                    System.out.println("Enter the number of snakes again");
                    snakes= reader.nextInt();
                }
                board.generateSnakes(3,boardSize);
                board.print();
            }
        } while (option!=2);
    }
}