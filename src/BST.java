public class BST {
    private static NodeScore root; //Es la raíz del árbol de búsqueda.

    public static NodeScore getRoot() {
        return root;
    }
    public static void setRoot(NodeScore root) {
        BST.root = root;
    }
    /*
         Anado los nodos de tipo nodeScore al arbol binario de busqueda en caso de que la root no este vacia.
        */
    public static void addNode(NodeScore current, NodeScore nodeScore) {
        //Meter a la izquierda
        if (nodeScore.getScore() < current.getScore()) { //Si el nodo entrante comparado con el nodo actual, es menor, lo ponemos a la izq
            if (current.getLeft() == null) { //Aqui es si el nodo de la izquierda esta vacia, si es a si, colocamos el nodo nuevo.
                current.setLeft(nodeScore);
            } else {
                addNode(current.getLeft(), nodeScore); //Si no, vamos a la izq y volvemos a hacer el llamado al metodo.
            }
        } else if (nodeScore.getScore() > current.getScore()) { //Si el nodo entrante comparado con el actual es mayor, lo ponemos a la der.
            //Meter a la derecha
            if (current.getRight() == null) { //Si la derecha del actual esta vacio, colocamos el nodo alli.
                current.setRight(nodeScore);
            } else {
                addNode(current.getRight(), nodeScore);//Si no, vamos a la der y volvemos a hacer el llamado al metodo.
            }

        } else {
            //No hacer nada
        }
    }
    /*
    Activador leaderBoard.
     */
    public static void leaderBoard() {
        leaderBoard(root);
    }
    /*Imprimir puntajes en orden ascendente
    Este metodo imprime los puntajes en orden descendente
     */
    private static void leaderBoard(NodeScore current) {
        if (current == null) { //Caso base, si el actual es ==null, retorna todo el metodo, el proceso de impresion inicia...
            return;
        }
        leaderBoard(current.getRight());//Como el mayor es el de la der, nos dirijimos a el, cuando no haya más izquierda, imprime, regresa a la rama principal y se va a la izq, asi hasta terminar con todos los datos.
        System.out.print("\nPlayer "+current.getPlayer().getIcon()+ " con un puntaje de " + current.getScore());
        leaderBoard(current.getLeft());
    }
}
