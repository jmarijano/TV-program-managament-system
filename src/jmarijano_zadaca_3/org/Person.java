package jmarijano_zadaca_3.org;

public class Person {

    private int Id;
    private String nameAndSurname;

    public Person() {
    }

    public Person(int id, String imePrezime) {
        Id = id;
        nameAndSurname = imePrezime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

}
