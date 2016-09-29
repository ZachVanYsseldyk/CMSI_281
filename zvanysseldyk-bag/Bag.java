import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Zachary Van Ysseldyk

class Bag implements Collectible {
    private Node first;
    private int n; // number of nodes
    private int total; // total number of words in this bag

    private int startingCount = 1;

    // Implement a constructor
    // public Bag() { ...
    public Bag() {
        this.first = null;
        this.n = 0;
        this.total = 0;
    }

    // Implement Collectible interface methods here
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return total;
    }

    public void add(String item) {
        boolean hasDouble = false;
        for(BagIterator i = new BagIterator(first); i.hasNext();) {
            Node current = i.next();
            if(current.item.equals(item)) {
                hasDouble = true;
                current.count += 1;
            }
        }
        if(!hasDouble) {
            Node newItem = new Node(first, item, startingCount);
            newItem.item = item;
            first = newItem;
            n++; 
        } 
        total++;
    }
    //not an actual thing
    public int uniqueSize() {
      return n;
    }

    public Obliterator iterator() {
        return new BagIterator(first);
    }

    class BagIterator implements Obliterator {
        private Node current;

        public BagIterator(Node first) {
            current = first;
        }

        public boolean hasNext() { 
            return current != null;  
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Node next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = current;
            current = current.next; 
            return node;
        }
    }
    public static void main(String[] args) throws IOException {
        Bag bag = new Bag();
        Scanner s = null;
        try {
            s = new Scanner(System.in);
            while (s.hasNext()) {
                String item = s.next(); // Scanner splits input on whitespace, by default
                bag.add(item);
                // System.out.println ("HELLO");
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        // Print bag size and distinct contents
        System.out.format("Total number of words: %d\n", bag.size());
        System.out.format("Unique number of words: %d\n", bag.uniqueSize());

        // Print distinct words in bag and their frequency
        for (Obliterator i = bag.iterator(); i.hasNext(); ) {
            Node node = i.next();
            System.out.format("%s %d\n", node.item, node.count);
        }
    }
}