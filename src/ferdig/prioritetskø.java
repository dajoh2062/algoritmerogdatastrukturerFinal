package ferdig;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class prioritetskø<T> {
    private static class Node<T> {
        private T verdi;
        private Node<T> neste;

        private Node(T verdi, Node<T> neste) {
            this.verdi = verdi;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null);
        }
    }

    private Node<T> førstePri;
    private final Comparator<T> c;

    public prioritetskø (Comparator<T> c) {
        this.c = c;
        førstePri = null;
    }

    public void legginn(T verdi) {
        if (førstePri == null || c.compare(verdi, førstePri.verdi) < 0) {
            førstePri = new Node<>(verdi, førstePri);
        } else {
            Node<T> p = førstePri;
            while (p.neste != null && c.compare(verdi, p.neste.verdi) < 0) {
                p = p.neste;
            }
            p.neste = new Node<>(verdi, p.neste);
        }
    }

    public T taut() {
        if (førstePri == null) throw new NoSuchElementException("Køen er tom");
        T verdi = førstePri.verdi;
        førstePri = førstePri.neste; // Flytt første til neste node
        return verdi;
    }

    public T se() {
        if (førstePri == null) throw new NoSuchElementException("Køen er tom");
        return førstePri.verdi;
    }

    public boolean tom() {
        return førstePri == null;
    }
}

