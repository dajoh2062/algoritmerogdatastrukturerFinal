package ferdig;

import java.util.Arrays;
import java.util.Comparator;

public class aMain {
    public static <T> void main(String[] args) {
        Comparator<Integer> c = Comparator.naturalOrder();
        prioritetskø<Integer> kø = new prioritetskø<>(c);

        kø.legginn(5);
        kø.legginn(3);
        kø.legginn(1);
        System.out.println(kø.toString());
        kø.legginn(8);
        kø.legginn(3);
        kø.legginn(2);

        System.out.println(kø.toString());

        kø.taut();
        kø.taut();
        System.out.println(kø.toString());
        System.out.println(c.compare(1,2));
        System.out.println(c.compare(1,1));
        System.out.println(c.compare(2,1));










    }


}
