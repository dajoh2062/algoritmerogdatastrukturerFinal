package ferdig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

class TabellListe<T> {
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
}
public class lzw {


    public static TabellListe<Integer> LZWkomprimer(String input) {
        TabellListe<Integer> kode = new TabellListe<>();
        char[] karakterTabell = input.toCharArray();
        String s;
        int kodeTall;
        int nesteKode= 256;
        // Runde 0
        char c = karakterTabell[0];
        s = ""+c;
        kodeTall = (int) c;
        ArrayList<String> ordbok = new ArrayList<>();
        for (int i = 1; i < karakterTabell.length; i++) {
            c = karakterTabell[i];
            // Er s + c i ordboka?
            if (ordbok.contains(s+c)) {
                s = s + c;
                kodeTall = finnKode(s, ordbok);
                // Sjekk neste bokstav
            } else {
                // Legg inn i ordboka
                ordbok.add(s+c);
                s = ""+c;
                // Gi ut koden
                kode.leggInn(kodeTall);
                kodeTall = finnKode(s, ordbok);
            }
        }
        kode.leggInn(kodeTall);
        return kode;
    }

    public static int finnKode(String s, ArrayList<String> ordbok) {
        if (s.length() == 1) {
            return (int) s.charAt(0);
        } else {
            return 256+ordbok.indexOf(s);
        }
    }
}