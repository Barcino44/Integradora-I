import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Board board = new Board();


        int actualTurn=1; //contador de turnos
        int snakes, ladders=0;
        int option, selectionOptionInmenuInTurn;
        int rows,columns,boardSize=0;

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
                System.out.println("Enter the number of columns that you want to add");
                while (!reader.hasNextInt()) {
                    reader.next();
                    System.out.println("Invalid, enter a level number");
                }
                columns = reader.nextInt();
                boardSize=columns*rows;
                for (int i = 1; i <= boardSize; i++) {
                    board.generateBoard(i);
                }
                board.print();
                while(actualTurn <= 4){ //Condicion de parada, se debe cambiar
                    board.showMenuInTurn(actualTurn);
                    selectionOptionInmenuInTurn=reader.nextInt();

                    if(selectionOptionInmenuInTurn==1) {

                        int dice = (int) (Math.random() * 6 + 1);
                        board.rollDice(dice, actualTurn);
                        board.print();
                        actualTurn++;
                        if(actualTurn==4){
                            actualTurn=1;
                        }
                    }
                    else{

                    }
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
            }
        } while (option!=2);
    }
}