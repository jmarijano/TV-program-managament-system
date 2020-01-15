package jmarijano_zadaca_3.COR;

public class NormalRevenueHandler implements Handler {

    private Handler handler;

    @Override
    public void handle(int number) {

        if (number < 30) {
            System.out.println("Potencijalni prihod reklama u vrijednosti od " + number
                    + " je manji od 30 te je prema tome donekle isplativo reklamirati.");
        } else {
            handler.handle(number);
        }
    }

    @Override
    public void setNext(Handler handler) {
        this.handler = handler;
    }

}
