package jmarijano_zadaca_3.COR;

public class HighRevenueIncomeHandler implements Handler {

    private Handler handler;

    @Override
    public void handle(int number) {
        if (number < 40) {
            System.out.println("Potencijalni prihod reklama u vrijednosti od " + number
                    + " je manji od 40 te je prema tome isplativo reklamirati.");
        } else {
            System.out.println("Potencijalni prihod reklama u vrijednosti od " + number
                    + " je veći od 40 te je prema tome dost isplativo reklamirati.");
        }
    }

    @Override
    public void setNext(Handler handler) {
        this.handler = handler;
    }

}
