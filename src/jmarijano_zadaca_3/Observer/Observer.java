package jmarijano_zadaca_3.Observer;

public interface Observer {

    void update(Subject subject, int newRoleId) throws Exception;

    void subscribe(Subject subject);

    void unSubscribe(Subject subject);
}
