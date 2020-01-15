package jmarijano_zadaca_3.Helpers;

import jmarijano_zadaca_3.Observer.Observer;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.PersonRole;

public class PersonRoleHelper {

    public static boolean isDuplicate(PersonRole personRole) {
        for (Subject subject : TVHouse.getInstance().getSubjects()) {
            PersonRole existingPersonRole = (PersonRole) subject;
            if (personRole.getPerson().getId() == existingPersonRole.getPerson().getId()
                    && personRole.getRole().getId() == existingPersonRole.getRole().getId()) {
                return true;
            }
        }
        return false;
    }

    public static boolean subscribe(Observer observer, Subject subject1) {
        PersonRole newOne = (PersonRole) subject1;
        for (Subject subject : TVHouse.getInstance().getSubjects()) {
            PersonRole existing = (PersonRole) subject;
            if (existing.getPerson().getId() == newOne.getPerson().getId()
                    && existing.getRole().getId() == newOne.getRole().getId()) {
                subject.subscribeObserver(observer);
                return true;
            }
        }
        return false;
    }
}
