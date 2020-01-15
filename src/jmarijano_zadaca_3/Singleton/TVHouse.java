package jmarijano_zadaca_3.Singleton;

import jmarijano_zadaca_3.org.Program;

import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.org.Show;
import jmarijano_zadaca_3.org.Person;
import jmarijano_zadaca_3.org.Role;
import jmarijano_zadaca_3.org.ShowType;

public class TVHouse {

    private static volatile TVHouse Instance;
    private static List<Program> programs;
    private static List<Role> roles;
    private static List<Person> persons;
    private static List<Show> shows;
    private static List<DayComposite> days;
    private static List<ShowType> showTypes;
    public static List<Subject> subjects;
    private static String filePath;

    public static TVHouse getInstance() {
        if (Instance == null) {
            synchronized ((TVHouse.class)) {
                Instance = new TVHouse();
                programs = new ArrayList<>();
                roles = new ArrayList<>();
                shows = new ArrayList<>();
                days = new ArrayList<>();
                showTypes = new ArrayList<>();
                subjects = new ArrayList<>();
            }
        }
        return Instance;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> aSubjects) {
        subjects = aSubjects;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        TVHouse.programs = programs;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        TVHouse.roles = roles;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> roles) {
        TVHouse.persons = roles;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        TVHouse.shows = shows;
    }

    public List<DayComposite> getDays() {
        return days;
    }

    public void setDays(List<DayComposite> days) {
        TVHouse.days = days;
    }

    public List<ShowType> getShowTypes() {
        return showTypes;
    }

    public void setShowTypes(List<ShowType> showTypes) {
        TVHouse.showTypes = showTypes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        TVHouse.filePath = filePath;
    }

}
