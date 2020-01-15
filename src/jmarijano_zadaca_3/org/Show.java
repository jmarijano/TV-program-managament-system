package jmarijano_zadaca_3.org;

import java.util.ArrayList;
import java.util.List;

public class Show {

    private int id;
    private String name;
    private int duration;
    private List<PersonRole> personRoles;
    private ShowType showType;

    public Show() {
        personRoles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<PersonRole> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(List<PersonRole> personRoles) {
        this.personRoles = personRoles;
    }

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return "Emisija id: " + this.getId()
                + " Naziv: " + this.getName()
                + " Trajanje: " + this.getDuration();

    }

}
