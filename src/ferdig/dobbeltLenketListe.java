package ferdig;

import java.util.*;

public class dobbeltLenketListe<T> implements Liste<T> {

    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }


    // ferdig.Oppgave 1
    public dobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public dobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a kan ikke være null!");

        for (T verdi : a) {
            if (verdi != null) {
                if (antall == 0) {
                    hode = hale = new Node<>(verdi);
                } else {
                    hale.neste = new Node<>(verdi, hale, null);
                    hale = hale.neste;
                }
                antall++;
            }
        }
    }


    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom(){
        return antall == 0;
    }

    // ferdig.Oppgave 2
    @Override
    public String toString() {
        Node<T> current = hode;
        StringBuilder ut = new StringBuilder("[");

        while (current != null) {
            ut.append(current.verdi);
            if (current.neste != null) {
                ut.append(", ");
            }
            current = current.neste;
        }

        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        Node<T> current = hale;
        StringBuilder out = new StringBuilder("[");

        while (current != null) {
            out.append(current.verdi);
            if (current.forrige != null) {
                out.append(", ");
            }
            current = current.forrige;
        }

        out.append("]");
        return out.toString();
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "verdien kan ikke være null");
        if (antall==0){
            hale=hode=new Node<>(verdi);
        }
        else{
            hale.neste = new Node<>(verdi, hale, null);
            hale=hale.neste;
        }
        antall++;
        endringer++;
        return true;
    }

    // ferdig.Oppgave 3
    private Node<T> finnNode(int indeks) {

        int sluttposisjon = antall-1;
        int startposisjon = 0;
        Node <T> current;

        if (2*indeks>sluttposisjon){
            current = hale;
            while (sluttposisjon > indeks){
                current = current.forrige;
                sluttposisjon--;
            }

        }
        else {
            current = hode;
            while (startposisjon < indeks){
                current = current.neste;
                startposisjon++;
            }

        }
        return current;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks,false);
        Objects.requireNonNull(nyverdi, "verdien kan ikke være null");

        T gammel = finnNode(indeks).verdi;
        finnNode(indeks).verdi= nyverdi;
        endringer++;
        return gammel;

    }


    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra,til);
        dobbeltLenketListe <T> produkt = new dobbeltLenketListe<>();
        Node <T> current = hode;
        int start = 0;

        while (start < til) {
            if (start >= fra){
                produkt.leggInn(current.verdi);
            }
            current = current.neste;
            start++;
        }
        return produkt;
    }

    // ferdig.Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        int start = 0;
        Node <T> current = hode;

        while (start < antall) {
            if(current.verdi.equals(verdi)){
                return start;
            }
            current = current.neste;
            start++;
        }
        return -1;

    }



    @Override
    public boolean inneholder(T verdi) {
        int start = 0;
        Node <T> current = hode;
        while (start < antall) {
            if(current.verdi.equals(verdi)){
                return true;
            }
            current = current.neste;
            start++;
        }
        return false;

    }

    // ferdig.Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        Objects.requireNonNull(verdi, "verdien kan ikke være null");

        Node<T> nyNode = new Node<>(verdi);

        if (hode == null) {
            hode = hale = nyNode;
        } else if (indeks == 0) {
            nyNode.neste = hode;
            hode.forrige = nyNode;
            hode = nyNode;
        } else if (indeks == antall) {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        } else {
            Node<T> peker1 = hode;
            while(indeks>0) {
                peker1 = peker1.neste;
                indeks--;
            }
            Node<T> peker2 = peker1.forrige;

            peker2.neste = nyNode;
            nyNode.forrige = peker2;
            nyNode.neste = peker1;
            peker1.forrige = nyNode;
        }

        antall++;
        endringer++;
    }

    // ferdig.Oppgave 6
    @Override
    public T fjern(int indeks) {

        if (indeks >= antall || indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks er utenfor lovlig område");
        }

        T verdi;

        if (hode == hale) {
            verdi = hode.verdi;
            hode = hale = null;
        } else if (indeks == 0) {
            verdi = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;


        } else if (indeks == antall - 1) {
            verdi = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            Node<T> peker1 = hode;
            for (int i = 0; i < indeks; i++) {
                peker1 = peker1.neste;
            }
            verdi = peker1.verdi;
            Node<T> peker0 = peker1.forrige;
            Node<T> peker2 = peker1.neste;

            peker0.neste = peker2;
            peker2.forrige = peker0;
        }

        antall--;
        endringer++;
        return verdi;
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> p;
        Node<T> q=hode;
        Node<T> r;
        int i =0;

        while (i<antall) {
            if (q.verdi.equals(verdi)) {
                endringer++;
                antall--;

                if(hode==hale){
                    q.verdi=null;
                    hode=null;
                    hale=null;
                    return true;

                }
                else if(i==0){
                    q.verdi=null;
                    r=q.neste;
                    q.neste=null;
                    r.forrige=null;
                    hode=r;
                    return true;


                }
                else if(i==antall-1){
                    q.verdi=null;
                    p=q.forrige;
                    q.forrige=null;
                    p.neste=null;
                    hale=p;
                    return true;

                }
                else{
                    p=q.forrige;
                    r=q.neste;
                    q.neste=null;
                    q.forrige=null;
                    p.neste=r;
                    r.forrige=p;
                    return true;
                }
            }
            q = q.neste;
            i++;
        }
        return false;
    }

    // ferdig.Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // ferdig.Oppgave 8

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        if (indeks < 0 || indeks >= antall) {
            throw new IndexOutOfBoundsException("Indeks: " + indeks + ", Antall: " + antall);
        }

        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;
            kanFjerne = false;
            iteratorendringer = endringer;
        }

        private DobbeltLenketListeIterator(int indeks) {
            if (indeks < 0 || indeks >= antall) {
                throw new IndexOutOfBoundsException("Indeks: " + indeks + ", Antall: " + antall);
            }

            denne = finnNode(indeks);
            kanFjerne = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret utenom iteratoren.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Ingen flere elementer i listen.");
            }

            kanFjerne = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
        }

        // ferdig.Oppgave 9:
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // ferdig.Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
