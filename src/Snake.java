public class Snake {
    private char head;
    private char tail;
    public Snake(char head, char tail) {
        this.head = head;
        this.tail = tail;
    }

    public char getHead() {
        return head;
    }

    public void setHead(char head) {
        this.head = head;
    }

    public char getTail() {
        return tail;
    }

    public void setTail(char tail) {
        this.tail = tail;
    }
}
