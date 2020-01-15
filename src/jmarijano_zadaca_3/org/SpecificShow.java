package jmarijano_zadaca_3.org;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Decorator.DefaultShowDecoration;
import jmarijano_zadaca_3.Observer.Observer;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.Singleton.TVHouse;

public class SpecificShow implements PlanComponent, Observer {

    private int id;
    private int day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Show show;
    private List<Subject> personRoles = new ArrayList<>();
    private PlanComponent parent;

    public SpecificShow() {
    }

    public SpecificShow(int id, int day, Show show, LocalTime endTime, LocalTime startTime,
            List<Subject> personRoles) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.show = show;
        this.personRoles = personRoles;
        this.id = id;

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Subject> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(List<Subject> personRoles) {
        this.personRoles = personRoles;
    }

    @Override
    public String toString() {
        return "Emisije: " + this.getShow()
                + " pocetak: " + this.getStartTime();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlanComponent getComponent() {
        return this;
    }

    @Override
    public SpecificShow copy() {
        SpecificShow novi = new SpecificShow(id, day, show, endTime, startTime, personRoles);
        novi.setParent(parent);
        return novi;
    }

    @Override
    public void update(Subject subject, int oldRole) throws Exception {
        boolean postoji = false;
        PersonRole da=(PersonRole)subject;
        for (Role uloga : TVHouse.getInstance().getRoles()) {
            if (uloga.getId() == da.getRole().getId()) {
                postoji = true;
                break;
            }
        }
        if (!postoji) {
            throw new Exception();
        }
        for (Subject osobaUlogaHelper : personRoles) {
            if (osobaUlogaHelper instanceof PersonRole) {
                PersonRole personRole = (PersonRole) osobaUlogaHelper;
                if (personRole.getPerson().getId() == da.getPerson().getId()
                        && personRole.getRole().getId() == oldRole) {
                    Role uloga = personRole.getRole();
                    uloga.setId(da.getRole().getId());
                    uloga.setDescription(da.getRole().getDescription());
                    personRole.setRole(uloga);
                }
            }
        }
    }

    @Override
    public void subscribe(Subject subject) {
        this.personRoles.add(subject);
    }

    @Override
    public void unSubscribe(Subject subject) {
        int index = this.personRoles.indexOf(subject);
        this.personRoles.remove(index);
    }

    @Override
    public void showData() {
        PlanComponent planComponent = new DefaultShowDecoration(this);
        planComponent.showData();
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlanComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(PlanComponent parent) {
        this.parent = parent;
    }

}
