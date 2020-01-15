package jmarijano_zadaca_3.Iterator;

public interface Iterator<T> {

    boolean hasNext();

    T getNext();

    void remove(T t);
}
