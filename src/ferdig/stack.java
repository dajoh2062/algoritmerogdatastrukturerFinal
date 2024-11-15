package ferdig;

public class stack<T> {
    private static final class Node<T> {
        private T verdi;
        private Node<T>neste;

        private Node(T verdi, Node<T> neste) {
            this.verdi = verdi; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null);}
    }



    public int antall;

    public String getantall(){
        return "Antall: "+ antall;
    }

    Node<T> øverst;

    public stack (){
        øverst=null;
    }

    public T taut(){
        if(øverst==null){
            throw new IndexOutOfBoundsException("Stacken er tom");
        }
        T verdi = øverst.verdi;
        Node fjern=øverst;
        øverst=fjern.neste;
        fjern.neste=null;
        fjern.verdi=null;
        antall--;
        return verdi;
    }
    public boolean legginn(T verdi){


        Node<T> p= new Node<>(verdi, øverst);
        øverst=p;

        antall++;
        return true;
    }
    public T se() {
        if(øverst==null){
            throw new IndexOutOfBoundsException("Stacken er tom");
        }
        return øverst.verdi;


    }
    public boolean tom(){
        return antall==0;
    }

}
