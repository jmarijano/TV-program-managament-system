package jmarijano_zadaca_3.COR;

public interface Handler {

    public void handle(int number);

    public void setNext(Handler handler);
}
