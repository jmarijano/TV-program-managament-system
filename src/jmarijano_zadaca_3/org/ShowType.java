package jmarijano_zadaca_3.org;

public class ShowType {

    private int id;
    private String name;
    private int advertisement;
    private int maxAdvertisementDuration;

    public ShowType() {
    }

    public ShowType(int id, String naziv, int imaReklame, int maksimalnoTrajanjeReklameMin) {
        this.id = id;
        this.name = naziv;
        this.advertisement = imaReklame;
        this.maxAdvertisementDuration = maksimalnoTrajanjeReklameMin;
    }

    public int getMaxAdvertisementDuration() {
        return maxAdvertisementDuration;
    }

    public void setMaxAdvertisementDuration(int maxAdvertisementDuration) {
        this.maxAdvertisementDuration = maxAdvertisementDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(int advertisement) {
        this.advertisement = advertisement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
