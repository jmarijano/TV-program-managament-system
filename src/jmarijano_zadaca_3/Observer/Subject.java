package jmarijano_zadaca_3.Observer;

public interface Subject {

    void subscribeObserver(Observer observer);

    void unSubscribeObserver(Observer observer);

    void notifyObservers(int newRoleId);
    
    void updateAll( int newRole);

}
