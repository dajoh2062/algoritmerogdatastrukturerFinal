package ferdig;

import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;

public class binærsøketre<T>  implements Beholder<T> {

    // En del kode er ferdig implementert, hopp til linje 91 for ferdig.Oppgave 1

    private static final class Node<T> { // En indre nodeklasse
        private T verdi; // Nodens verdi
        private Node<T> venstre, høyre, forelder; // barn og forelder


        private Node(T verdi, Node<T> v, Node<T> h, Node<T> f) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            forelder = f;
        }

        private Node(T verdi, Node<T> f) {
            this(verdi, null, null, f);
        }

        @Override
        public String toString() {
            return verdi.toString();
        }
    }

    private final class SBTIterator implements Iterator<T> {
        Node<T> neste;

        public SBTIterator() {
            neste = førstePostorden(rot);
        }

        public boolean hasNext() {
            return (neste != null);
        }

        public T next() {
            Node<T> denne = neste;
            neste = nestePostorden(denne);
            return denne.verdi;
        }
    }

    public Iterator<T> iterator() {
        return new SBTIterator();
    }

    private Node<T> rot;
    private int antall;
    private int endringer;

    private final Comparator<? super T> comp;

    public binærsøketre(Comparator<? super T> c) {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;
        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot);
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }


    // ferdig.Oppgave 1
    public boolean leggInn(T verdi) {
        if (verdi == null) throw new NullPointerException("Objektet kan ikke være null.");

        Node<T> p = rot;
        int cmp;

        if (p == null) {
            rot = new Node<>(verdi, null);
            antall++;
            return true;

        }

        while (true) {
            cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                if (p.venstre == null) {
                    p.venstre = new Node<>(verdi, p);
                    antall++;
                    break;
                }
                p = p.venstre;
            } else {
                if (p.høyre == null) {
                    p.høyre = new Node<>(verdi, p);
                    antall++;
                    break;
                }
                p = p.høyre;
            }

        }
        return true;


    }

    // ferdig.Oppgave 2
    public int antall(T verdi) {
        if (tom() || !inneholder(verdi) || verdi == null) return 0;

        int count = 0;
        Node<T> p = rot;
        int cmp;

        while (true) {
            if (p == null) break;
            cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else if (cmp > 0) {
                p = p.høyre;

            } else {
                count++;
                p = p.høyre;

            }
        }
        return count;


    }

    // ferdig.Oppgave 3
    private Node<T> førstePostorden(Node<T> p) {

        if (p.venstre != null) {
            return førstePostorden(p.venstre);
        } else if (p.høyre != null) {
            return førstePostorden(p.høyre);
        } else {
            return p;
        }


    }

    private Node<T> nestePostorden(Node<T> p) {

        if (p.forelder == null) {
            return null;
        }


        if (p == p.forelder.venstre && p.forelder.høyre != null) {
            return førstePostorden(p.forelder.høyre);
        }


        return p.forelder;

    }

    // ferdig.Oppgave 4
    public void postOrden(Oppgave<? super T> oppgave) {
        if (rot == null) return;

        Node<T> p = førstePostorden(rot);

        while (p != null) {

            oppgave.utførOppgave( p.verdi);
            p = nestePostorden(p);
        }
    }

    public void postOrdenRekursiv(Oppgave<? super T> oppgave) {
        postOrdenRekursiv(rot, oppgave); // Ferdig implementert
    }

    private void postOrdenRekursiv(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null) return;

        postOrdenRekursiv(p.venstre, oppgave);

        postOrdenRekursiv(p.høyre, oppgave);

        oppgave.utførOppgave(p.verdi);
    }

    // ferdig.Oppgave 5
    public boolean fjern(T verdi) {
        if (verdi == null) return false;

        Node<T> q = null;
        Node<T> p = rot;
        int cmp;

        while (p != null) {
            cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                q = p;
                p = p.venstre;
            } else if (cmp > 0) {
                q = p;
                p = p.høyre;
            } else break;
        }

        if (p == null) return false;

        if (p.venstre == null && p.høyre == null) {
            if (p == rot) {
                rot = null;
            } else if (p == q.venstre) {
                q.venstre = null;
            } else {
                q.høyre = null;
            }
            p.verdi = null;
            p.forelder = null;
        } else if (p.høyre == null) {
            if (p == rot) {
                rot = p.venstre;
                rot.forelder = null;
            } else if (p == q.venstre) {
                q.venstre = p.venstre;
                q.venstre.forelder = q;
            } else {
                q.høyre = p.venstre;
                q.høyre.forelder = q;
            }
            p.verdi = null;
            p.venstre = null;
            p.forelder = null;
        } else if (p.venstre == null) {
            if (p == rot) {
                rot = p.høyre;
                rot.forelder = null;
            } else if (p == q.venstre) {
                q.venstre = p.høyre;
                q.venstre.forelder = q;
            } else {
                q.høyre = p.høyre;
                q.høyre.forelder = q;
            }
            p.verdi = null;
            p.høyre = null;
            p.forelder = null;
        } else {
            Node<T> r = p.høyre;
            Node<T> s = p;

            while (r.venstre != null) {
                s = r;
                r = r.venstre;
            }

            p.verdi = r.verdi;

            if (s != p) {
                s.venstre = r.høyre;
            } else {
                s.høyre = r.høyre;
            }

            if (r.høyre != null) {
                r.høyre.forelder = s;
            }

            r.verdi = null;
            r.høyre = null;
            r.forelder = null;
        }

        antall--;
        endringer++;
        return true;
    }


    public int fjernAlle(T verdi) {
        if (verdi == null) return 0;

        int antallFjernet = 0;
        while (fjern(verdi)) {
            antallFjernet++;
        }

        return antallFjernet;
    }

    public void nullstill() {
        nullstillRekursiv(rot);
        rot = null;
        antall = 0;
    }

    private void nullstillRekursiv(Node<T> node) {
        if (node != null) {

            nullstillRekursiv(node.venstre);
            nullstillRekursiv(node.høyre);


            node.venstre = null;
            node.høyre = null;
            node.verdi = null;
            node.forelder=null;
        }
    }
}
