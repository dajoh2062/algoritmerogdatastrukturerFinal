package ferdig;

import java.util.NoSuchElementException;

public class queue<T> {
    private static final class Node<T> {
        private T verdi;
        private Node<T> neste;
        private Node<T> forrige;

        private Node(T verdi, Node<T> neste, Node<T> forrige) {
            this.verdi = verdi;
            this.neste = neste;
            this.forrige = forrige;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    private int antall;
    private Node<T> første;
    private Node<T> siste;

    public queue() {
        første = null;
        siste = null;
        antall = 0;
    }

    public String getantall() {
        return "Antall: " + antall;
    }

    @Override
    public String toString() {
        StringBuilder ut = new StringBuilder("[");
        Node<T> current = første;
        while (current != null) {
            ut.append(current.verdi).append(", ");
            current = current.forrige;
        }
        if (antall > 0) {
            ut.setLength(ut.length() - 2); // Remove trailing comma and space
        }
        ut.append("]");
        return ut.toString();
    }

    public T taut() {
        if (første == null) throw new NoSuchElementException("Køen er tom");
        T verdi = første.verdi;
        if (første == siste) {
            første = siste = null; // Queue becomes empty
        } else {
            Node<T> p =første;
            første = første.forrige;
            første.neste = null;
            p.forrige=null;
            p.verdi = null;
        }
        antall--;
        return verdi;
    }

    public T se() {
        if (første == null) throw new NoSuchElementException("Køen er tom");
        return første.verdi;
    }

    public void legginn(T verdi) {
        Node<T> p = new Node<>(verdi);
        if (siste == null) {
            første = siste = p;
        } else {
            siste.forrige = p;
            p.neste=siste;
            siste = p;
        }
        antall++;
    }
}
