package jmarijano_zadaca_3.org;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmarijano_zadaca_3.Helpers.RoleHelper;
import jmarijano_zadaca_3.Observer.Observer;
import jmarijano_zadaca_3.Observer.Subject;

public class PersonRole implements Subject {

    private Person person;
    private Role role;
    private List<Observer> observers = new ArrayList<>();

    public PersonRole() {
    }

    public PersonRole(Person person, Role role) {
        this.person = person;
        this.role = role;

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void subscribeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unSubscribeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        observers.remove(index);
    }

    @Override
    public void notifyObservers(int oldRole) {
        for (Observer observer : this.observers) {
            try {
                observer.update(this, oldRole);
            } catch (Exception ex) {
                Logger.getLogger(PersonRole.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateAll(int newRole) {
        int oldROle = this.role.getId();
        this.role=RoleHelper.getUloga(newRole);
        notifyObservers(oldROle);
    }
}
