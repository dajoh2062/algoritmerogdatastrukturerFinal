package ferdig;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class TabellListe<T> implements Iterable<T> {

        private T[] tabellListe;
        private int antall;

    @SuppressWarnings("unchecked")
    public TabellListe(int størrelse) {
        int antall = 0;
        tabellListe = (T[]) new Object[størrelse];
    }

        public String toString() {
        return Arrays.toString(Arrays.copyOf(tabellListe, antall));
    }

    public TabellListe() {
        this(10);
    }

        public int antall() {
        return antall;
    }

        public boolean tom() {
        return antall == 0;
    }

        @SuppressWarnings("unchecked")
        public boolean leggInn(T t) {
        Objects.requireNonNull(t, "Nullelement ikke lov å legge inn");
        if (antall == tabellListe.length) {
            Object[] nyTabellListe = new Object[2 * tabellListe.length];
            for (int i = 0; i < antall; i++) {
                nyTabellListe[i] = tabellListe[i];
            }
            tabellListe = (T[]) nyTabellListe;
        }
        tabellListe[antall++] = t;

        return true;
    }

        public T hent(int i) {
        if (i < 0 || i >= antall) {
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        }
        return tabellListe[i];
    }

        public T oppdater(int i, T t) {
        if (i < 0 || i >= antall) {
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        }
        T gammelVerdi = tabellListe[i];
        tabellListe[i] = t;
        return gammelVerdi;
    }

        public boolean leggInn(int i, T t) {
        throw new UnsupportedOperationException();
    }

        public boolean fjern(T t) {
        throw new UnsupportedOperationException();
    }

        public boolean fjern(int i) {
        throw new UnsupportedOperationException();
    }

        public boolean inneholder(T t) {
        throw new UnsupportedOperationException();
    }

        public Iterator<T> iterator() {
        return new TabellListeIterator();
    }

        private class TabellListeIterator implements Iterator<T> {
            int i;

            public TabellListeIterator() {
                i = 0;
            }

            public boolean hasNext() {
                return i < antall;
            }

            public T next() {
                return tabellListe[i++];
            }
        }

    public static void main(String[] args) {

        TabellListe<Integer> liste = new TabellListe<>(10);
        liste.leggInn(2);
        liste.leggInn(3);
        liste.leggInn(4);
        System.out.println(liste.toString());
        System.out.println(liste.antall());


        Iterator<Integer> it = liste.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        for(Integer i : liste) {
            System.out.println(i);
        }
    }
    }



