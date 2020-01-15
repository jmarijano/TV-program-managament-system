package jmarijano_zadaca_3.COR;

public class LowRevenueIncomeHandler implements Handler {

    private Handler handler;

    public LowRevenueIncomeHandler() {
    }

    @Override
    public void handle(int number) {
        if (number < 20) {
            System.out.println("Potencijalni prihod reklama u vrijednosti od " + number
                    + " je manji od 20 te je prema tome neisplativo reklamirati.");

        } else if (handler != null) {
            handler.handle(number);
        }
    }

    @Override
    public void setNext(Handler handler) {
        this.handler = handler;
    }

}
