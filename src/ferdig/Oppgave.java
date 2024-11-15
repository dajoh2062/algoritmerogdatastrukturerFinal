package ferdig;

@FunctionalInterface
public interface Oppgave<T> {
    void utførOppgave(T t);
}