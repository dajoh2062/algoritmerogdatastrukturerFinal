package ferdig;

import java.util.NoSuchElementException;

public class deque<T> {
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

        public deque() {
            første = null;
            siste = null;
            antall = 0;
        }

        public String getAntall() {
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
                ut.setLength(ut.length() - 2); // Fjerner trailing komma og mellomrom
            }
            ut.append("]");
            return ut.toString();
        }

        public T fjernForan() {
            if (første == null) throw new NoSuchElementException("Køen er tom");
            T verdi = første.verdi;
            if (første == siste) {
                første = siste = null;
            } else {
                Node<T> p = første;
                første = første.forrige;
                første.neste = null;
                p.forrige = null;
                p.verdi = null;
            }
            antall--;
            return verdi;
        }

        public T fjernBak() {
            if (siste == null) throw new NoSuchElementException("Køen er tom");
            T verdi = siste.verdi;
            if (første == siste) {
                første = siste = null;
            } else {
                Node<T> p = siste;
                siste = siste.neste;
                siste.forrige = null;
                p.neste = null;
                p.verdi = null;
            }
            antall--;
            return verdi;
        }

        public T seForan() {
            if (første == null) throw new NoSuchElementException("Køen er tom");
            return første.verdi;
        }

        public T seBak() {
            if (siste == null) throw new NoSuchElementException("Køen er tom");
            return siste.verdi;
        }

        public void leggInnForan(T verdi) {
            Node<T> p = new Node<>(verdi);
            if (første == null) {
                første = siste = p;
            } else {
                p.forrige = første;
                første.neste = p;
                første = p;
            }
            antall++;
        }

        public void leggInnBak(T verdi) {
            Node<T> p = new Node<>(verdi);
            if (siste == null) {
                første = siste = p;
            } else {
                siste.forrige = p;
                p.neste = siste;
                siste = p;
            }
            antall++;
        }
    }


